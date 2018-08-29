package org.application.controller;

import org.application.businesslogic.messagebl.MessageService;
import org.application.businesslogic.shoppingbl.ShoppingService;
import org.application.businesslogic.tagbl.TagService;
import org.application.util.*;
import org.application.vo.CommodityVO;
import org.application.vo.MessageVO;
import org.application.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.application.util.ConstantString.*;
import static org.application.util.PostType.PIC;
import static org.application.util.PostType.VIDEO;

/**
 * post is the combination of Message and Commodity
 * @author miaomuzhi
 * @since 2018/8/27
 */
@RestController
public class PostController {

    private TagService tagService;
    private ShoppingService shoppingService;
    private MessageService messageService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }


    /**
     * 分享一篇笔记,成功返回'SUCCESS’, 后端需要设置writer为当前登录用户, 设置一个唯一标识id
     */
    @RequestMapping("/share_post")
    public ResultMessage sharePost(@RequestParam String title, @RequestParam MultipartFile[] covers,
                                   @RequestParam MultipartFile video, @RequestParam PostType fileType,
                                   @RequestParam List<String> tags, @RequestParam double price,
                                   @RequestParam String type, @RequestParam String content,
                                   HttpSession session){

        if (type.equals(SHARE)){
            return addMessage((Long) session.getAttribute(USER_ID), title, Arrays.asList(covers), video, fileType, tags, content);
        } else if (type.equals(SELL)){
            return addCommodity((Long) session.getAttribute(USER_ID), title, Arrays.asList(covers), video, fileType, tags, price, content);
        } else {
            LoggerUtil.getLogger().info(new Exception("an unknown type"));
            return ResultMessage.FAILURE;
        }
    }

    /**
     * get post by ID
     * @param request includes postID
     * @param response includes Post's json String
     */
    @RequestMapping("/get_post")
    public void getPost(HttpServletRequest request, HttpServletResponse response){
        long postID = Long.parseLong(request.getParameter("postID"));
        String type = request.getParameter("type");
        switch (type){
            case SELL:
                CommodityVO commodityVO = shoppingService.findCommodityById(postID);
                if (commodityVO != null) {
                    JsonSendUtil.sendJson2Browser(JsonUtil.toJson(commodityVO), response);
                }
                break;
            case SHARE:
                MessageVO messageVO = messageService.findMessageById(postID);
                if (messageVO != null){
                    JsonSendUtil.sendJson2Browser(JsonUtil.toJson(messageVO), response);
                }
                break;

                default:
                    LoggerUtil.getLogger().info(new Exception("unknown type"));
        }
    }

    /**
     *审核一篇笔记，可选择不通过，通过，加精，加精的笔记在一期一会版面展示，后端需要设置post的status
     * @param checkResult fail, pass, recommend, recommend表示加精
     * @param postID id of post
     * @return 审核成功返回'SUCCESS'
     */
    @RequestMapping("/check_post")
    public ResultMessage checkPost(@RequestParam State checkResult, @RequestParam long postID, @RequestParam String type){
        switch (type){
            case SELL:
                CommodityVO commodityVO = shoppingService.findCommodityById(postID);
                if (commodityVO != null){
                    commodityVO.setStatus(checkResult);
                    return shoppingService.modifyCommodity(commodityVO);
                }
                break;
            case SHARE:
                MessageVO messageVO = messageService.findMessageById(postID);
                if (messageVO != null){
                    messageVO.setStatus(checkResult);
                    return messageService.modifyMessage(messageVO);
                }
                break;

                default:
                    LoggerUtil.getLogger().info(new Exception("unknown type"));
                    break;
        }
        return ResultMessage.FAILURE;
    }

    /**
     * 得到作者最近几篇分享的笔记
     * @param request includes writer
     * @param response 返回post[]，数组的长度不超过5
     */
    @RequestMapping("/get_recent_posts")
    public void getRecentPosts(HttpServletRequest request, HttpServletResponse response){
        long writerId = Long.parseLong(request.getParameter("writer"));

        List<MessageVO> messageVOS = messageService.findAllMessages();
        messageVOS.removeIf(messageVO -> messageVO.getWriter() != writerId);

        if (messageVOS.size() > 5){
            messageVOS = messageVOS.subList(messageVOS.size() - 5, messageVOS.size());
        }
        JsonSendUtil.sendJson2Browser(JsonUtil.toJson(messageVOS), response);
    }

    /**
     * 根据用户和笔记状态返回其全部的笔记
     * @param request writer state
     * @param response post[]
     */
    @RequestMapping("/get_state_posts")
    public void getStatePosts(HttpServletRequest request, HttpServletResponse response){
        long writerId = Long.parseLong(request.getParameter("writer"));
        String state = request.getParameter("state");

        State status;
        switch (state){
            case "pass":
                status = State.PERMITTED;
                break;
            case "fail":
                status = State.DENIED;
                break;
            case "uncheck":
                status = State.WAITING;
                break;
            case "highlight":
                status = State.RECOMMENDED;
                break;

                default:
                    LoggerUtil.getLogger().info(new Exception("unknown state enum"));
                    return;
        }

        List<CommodityVO> commodityVOS = shoppingService.findAllCommodities();
        commodityVOS.removeIf(commodityVO -> commodityVO.getWriter() != writerId || commodityVO.getStatus() != status);
        List<MessageVO> messageVOS = messageService.findAllMessages();
        messageVOS.removeIf(messageVO -> messageVO.getWriter() != writerId || messageVO.getStatus() != status);

        JsonSendUtil.sendJson2Browser(JsonUtil.toJson(mergePosts(messageVOS, commodityVOS)), response);
    }

    /**
     * 按状态获取所有笔记
     * @param request status, State
     * @param response  list of posts
     */
    @RequestMapping("/receive_all_posts")
    public void receiveAllPosts(HttpServletRequest request, HttpServletResponse response){
        State state = State.valueOf(request.getParameter("status"));
        List<CommodityVO> commodityVOS = shoppingService.findAllCommodities();
        commodityVOS.removeIf(commodityVO -> commodityVO.getStatus() != state);
        List<MessageVO> messageVOS = messageService.findAllMessages();
        messageVOS.removeIf(messageVO -> messageVO.getStatus() != state);

    }

    /**
     * 按标签获取所有文章，返回post[]
     * @param tag 标签
     * @return list of posts
     */
    @RequestMapping("/receive_posts")
    public List<MessageVO> receivePosts(@RequestParam String tag){
        TagVO tagVO = tagService.findTagByContent(tag);
        return messageService.findMessageByTag(tagVO);
    }

    /**
     * 按标签获取所有商品，返回commodities[]
     * @param tag 标签
     * @return list of posts
     */
    @RequestMapping("/receive_commodities")
    public List<CommodityVO> receiveCommodities(@RequestParam String tag){
        List<CommodityVO> commodityVOS = shoppingService.findAllCommodities();
        commodityVOS.removeIf(commodityVO -> {
            boolean isNotIncluded = true;
            for (TagVO vo : commodityVO.getTagVOS()) {
                if (vo.getContent().equals(tag)){
                    isNotIncluded = false;
                    break;
                }
            }
            return isNotIncluded;
        });
        return commodityVOS;
    }

    /**
     * add post as message
     */
    private ResultMessage addMessage(long writerId, String title, List<MultipartFile> covers, MultipartFile video, PostType fileType, List<String> tags, String content){
        MessageVO messageVO = new MessageVO();
        messageVO.setWriter(writerId);
        messageVO.setTitle(title);
        messageVO.setTags(getTagsInBatch(tags));
        messageVO.setPostType(fileType);
        messageVO.setContent(content);
        messageVO.setStatus(State.WAITING);

        if (fileType == PIC){//上传封面图片
            messageVO.setCovers(uploadImages(covers));
        } else if (fileType == VIDEO){//上传视频
            messageVO.setVideo(uploadVideo(video));
        } else {
            LoggerUtil.getLogger().warning(new Exception("unknown fileType"));
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * add post as commodity
     */
    private ResultMessage addCommodity(long writerId, String title, List<MultipartFile> covers, MultipartFile video, PostType fileType, List<String> tags, double price, String content){
        CommodityVO commodityVO = new CommodityVO();
        commodityVO.setWriter(writerId);
        commodityVO.setTitle(title);
        commodityVO.setTagVOS(getTagsInBatch(tags));
        commodityVO.setPostType(fileType);
        commodityVO.setRemainedQuantity(100);//随便设个值
        commodityVO.setPrice(price);
        commodityVO.setContent(content);
        commodityVO.setStatus(State.WAITING);

        if (fileType == PIC){
            commodityVO.setCovers(uploadImages(covers));
        } else if (fileType == VIDEO){
            commodityVO.setVideo(uploadVideo(video));
        } else {
            LoggerUtil.getLogger().warning(new Exception("unknown fileType"));
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * utility function, upload images as covers
     * @param covers list of file
     * @return list of urls of covers
     */
    private List<String> uploadImages(List<MultipartFile> covers){
        List<String> urls = new ArrayList<>(covers.size());
        int count = 0;
        for (MultipartFile cover : covers) {
            count ++;

            String dirPath = PathUtil.imageUploadPath + SLASH + "covers" + SLASH + count;
            if(FileUtil.uploadImage(cover, dirPath) == ResultMessage.FAILURE){
                LoggerUtil.getLogger().info(new Exception("upload image failed"));
            }else urls.add(PathUtil.imageContextPath + SLASH + "covers" + SLASH + count + SLASH + cover.getOriginalFilename());
        }
        return urls;
    }

    /**
     * utility function
     * @param video file
     * @return url of video
     */
    private String uploadVideo(MultipartFile video){
        String dirPath = PathUtil.imageUploadPath + SLASH + "videos";
        if(FileUtil.uploadImage(video, dirPath) == ResultMessage.FAILURE){
            LoggerUtil.getLogger().info(new Exception("upload video failed"));
            return null;
        }
        return PathUtil.imageContextPath + SLASH + "videos" + SLASH + video.getOriginalFilename();
    }

    /**
     * get tagVOs by tag content in batch
     * @param tags list of tags' content
     * @return  list of tagVO
     */
    private List<TagVO> getTagsInBatch(List<String> tags){
        List<TagVO> tagVOs = new ArrayList<>(tags.size());
        tags.forEach(tag -> tagVOs.add(tagService.findTagByContent(tag)));
        return tagVOs;
    }

    /**
     * merge different posts into a list
     */
    @SuppressWarnings("unchecked")
    private List mergePosts(List<MessageVO> messageVOS, List<CommodityVO> commodityVOS){
        List sum = new ArrayList(commodityVOS.size() + messageVOS.size());
        sum.addAll(commodityVOS);
        sum.addAll(messageVOS);
        return sum;
    }

    public static void main(String[] args){
        MessageVO messageVO1 = new MessageVO();
        MessageVO messageVO2 = new MessageVO();
        CommodityVO commodityVO = new CommodityVO();
        ArrayList list = new ArrayList<>(Arrays.asList(messageVO1, messageVO2, commodityVO));
        System.out.println(list.getClass());
        System.out.println(JsonUtil.toJson(list));
    }
}
