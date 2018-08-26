package org.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.util.JsonUtil;
import org.util.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author 王川源
 */
public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //保存当前用户
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        request.getSession().setAttribute("userId", Long.parseLong(userId));
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JsonUtil.toJson(ResultMessage.SUCCESS));
    }

}
