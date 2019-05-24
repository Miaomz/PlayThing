package org.application.controller;

import org.application.businesslogic.tagbl.TagService;
import org.application.businesslogic.userbl.UserService;
import org.application.po.UserPO;
import org.application.security.MyUserDetails;
import org.application.util.FileUtil;
import org.application.util.LoggerUtil;
import org.application.util.PathUtil;
import org.application.util.ResultMessage;
import org.application.vo.TagVO;
import org.application.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.application.util.ConstantString.*;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@RestController
public class UserController {

    private UserService userService;
    private TagService tagService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping("/current_user")
    public UserVO currentUser(HttpSession session){
        Object userId = session.getAttribute(USER_ID);
        if (userId == null){
            return null;
        } else {
            return userService.findUserById((Long) userId);
        }
    }

    /**
     * 返回的是id 如果注册失败就返回负数
     */
    @RequestMapping("/register")
    public long register(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam String userName, @RequestParam String password, @RequestParam(required = false) MultipartFile avatar,
                                  @RequestParam String location, @RequestParam String mail, @RequestParam String phone, @RequestParam(required = false) String[] tags){
        if(tags != null)
            saveTags(Arrays.asList(tags));

        UserVO userVO = new UserVO();
        userVO.setUserName(userName);
        userVO.setPassword(password);
        userVO.setDisplay(uploadAvatar(userName, avatar));
        userVO.setLocation(location);
        userVO.setMail(mail);
        userVO.setPhone(phone);
        if (tags != null)
            userVO.setTags(getTagVOsInBatch(Arrays.asList(tags)));


        userVO.setRole(ROLE_USER);

        ResultMessage resultMessage = userService.addUser(userVO);

        try {
            if (resultMessage == ResultMessage.SUCCESS) {
                //实现自动登录
                MyUserDetails userDetails = new MyUserDetails((UserPO) userVO.toPO());
                //经过认证的token，伪造
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                request.getSession().setAttribute(USER_ID, userService.findUserByName(userName).getUserId());
            }
        } catch (Exception e) {
            LoggerUtil.getLogger().info(e);
        }
        return (resultMessage==ResultMessage.SUCCESS) ? userService.findUserByName(userName).getUserId() : -1;
    }

    @RequestMapping("/get_user")
    public UserVO getUser(@RequestParam long user){
        return userService.findUserById(user);
    }

    @RequestMapping("/editPerInfo")
    public ResultMessage editPersonInfo(@RequestParam String userName, @RequestParam(required = false) MultipartFile avatar,
                                        @RequestParam String location, @RequestParam String mail,
                                        @RequestParam String phone, @RequestParam(required = false) String[] tags){
        if(tags != null)
            saveTags(Arrays.asList(tags));

        UserVO userVO = userService.findUserByName(userName);
        if (userVO == null){
            return ResultMessage.FAILURE;
        }

        userVO.setLocation(location);
        userVO.setMail(mail);
        userVO.setPhone(phone);
        userVO.setDisplay(uploadAvatar(userName, avatar));
        if (tags != null)
            userVO.setTags(getTagVOsInBatch(Arrays.asList(tags)));

        return userService.modifyUser(userVO);
    }

    @RequestMapping("/adminLogin")
    public ResultMessage adminLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        UserVO userVO = userService.findUserByName(username);
        if (userVO == null){
            return ResultMessage.INEXISTENCE;
        }

        if (userVO.getPassword().equals(password)){
            MyUserDetails userDetails = new MyUserDetails((UserPO) userVO.toPO());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            token.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            request.getSession().setAttribute(USER_ID, userService.findUserByName(username).getUserId());
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.WRONG_PASS;
        }
    }

    @GetMapping("/dummy")
    public String dummy(){
        return "<html><head><title>dummy</title></head><body><p>Get method testing</p><p>medicine</p></body></html>";
    }
    
    private String uploadAvatar(String userName, MultipartFile avatar){
        if (avatar == null) {
            return null;
        }

        String dirPath = PathUtil.imageUploadPath + SLASH + "avatars" + SLASH + userName;
        if (FileUtil.uploadImage(avatar, dirPath) == ResultMessage.FAILURE){
            LoggerUtil.getLogger().info(new Exception("upload avatar failed"));
            return null;
        } else {
            return PathUtil.imageContextPath + SLASH + "avatars" + SLASH + userName + SLASH + avatar.getOriginalFilename();
        }
    }

    private List<TagVO> getTagVOsInBatch(List<String> tagIds){
        if(tagIds == null)
            return new ArrayList<>();
        List<TagVO> tagVOS = new ArrayList<>(tagIds.size());
        tagIds.forEach(tag -> tagVOS.add(tagService.findTagByContent(tag)));
        tagVOS.removeIf(Objects::isNull);
        return tagVOS;
    }

    private void saveTags(List<String> tags){
        if (tags == null){
            return;
        }

        tags.forEach(tag -> {
            if (tagService.findTagByContent(tag) == null){
                TagVO tagVO = new TagVO();
                tagVO.setContent(tag);
                tagService.addTag(tagVO);
            }
        });
    }
}
