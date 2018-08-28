package org.controller;

import org.businesslogic.messagebl.MessageService;
import org.businesslogic.shoppingbl.ShoppingService;
import org.businesslogic.tagbl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.util.*;
import org.vo.CommodityVO;
import org.vo.MessageVO;
import org.vo.TagVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.util.ConstantString.SLASH;
import static org.util.ConstantString.USER_ID;

/**
 * post is the combination of Message and Commodity
 * @author miaomuzhi
 * @since 2018/8/27
 */
@Controller
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
    public ResultMessage sharePost(@RequestParam String title, @RequestParam List<MultipartFile> covers,
                                   @RequestParam MultipartFile video, @RequestParam String fileType,
                                   @RequestParam List<String> tags, @RequestParam double price,
                                   @RequestParam String type, @RequestParam String content,
                                   HttpSession session){

        if (type.equals("share")){
            return addMessage((Long) session.getAttribute(USER_ID), title, covers, video, fileType, tags, content);
        } else if (type.equals("sell")){
            return addCommodity((Long) session.getAttribute(USER_ID), title, covers, video, fileType, tags, price, content);
        } else {
            LoggerUtil.getLogger().info(new Exception("unknown type"));
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
        //TODO
    }

    /**
     * 得到作者最近几篇分享的笔记
     * @param request includes writer
     * @param response 返回post[]，数组的长度不超过5
     */
    @RequestMapping("/get_recent_posts")
    public void getRecentPosts(HttpServletRequest request, HttpServletResponse response){
        long writerId = Long.parseLong(request.getParameter("writer"));
        //TODO
    }

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


    }


    /**
     * add post as message
     */
    private ResultMessage addMessage(long writerId, String title, List<MultipartFile> covers, MultipartFile video, String fileType, List<String> tags, String content){
        MessageVO messageVO = new MessageVO();
        messageVO.setWriter(writerId);
        messageVO.setTitle(title);
        messageVO.setTags(getTagsInBatch(tags));
        messageVO.setContent(content);
        messageVO.setStatus(State.WAITING);

        if (fileType.equals("pic")){//上传封面图片
            messageVO.setPostType(PostType.PIC);
            messageVO.setCovers(uploadImages(covers));
        } else if (fileType.equals("video")){//上传视频
            messageVO.setPostType(PostType.VIDEO);
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
    private ResultMessage addCommodity(long writerId, String title, List<MultipartFile> covers, MultipartFile video, String fileType, List<String> tags, double price, String content){
        CommodityVO commodityVO = new CommodityVO();
        commodityVO.setWriter(writerId);
        commodityVO.setTitle(title);
        commodityVO.setTagVOS(getTagsInBatch(tags));
        commodityVO.setRemainedQuantity(100);//随便设个值
        commodityVO.setPrice(price);
        commodityVO.setContent(content);
        commodityVO.setStatus(State.WAITING);

        if (fileType.equals("pic")){
            commodityVO.setPostType(PostType.PIC);
            commodityVO.setCovers(uploadImages(covers));
        } else if (fileType.equals("video")){
            commodityVO.setPostType(PostType.VIDEO);
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

    public static void main(String[] args){
        MessageVO messageVO1 = new MessageVO();
        MessageVO messageVO2 = new MessageVO();
        System.out.println(JsonUtil.toJson(new ArrayList<>(Arrays.asList(messageVO1, messageVO2))));
    }
}