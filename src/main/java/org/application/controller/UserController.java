package org.application.controller;

import org.application.businesslogic.userbl.UserService;
import org.application.util.ResultMessage;
import org.application.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static org.application.util.ConstantString.USER_ID;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/current_user")
    public UserVO currentUser(HttpSession session){
        return userService.findUserById((Long) session.getAttribute(USER_ID));
    }

    @RequestMapping("/register")
    public ResultMessage register(){
        //TODO
        return null;
    }

    @RequestMapping("/get_user")
    public UserVO getUser(@RequestParam long user){
        return userService.findUserById(user);
    }
}
