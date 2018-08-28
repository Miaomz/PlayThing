package org.application.security;

import org.application.util.ConstantString;
import org.application.util.JsonUtil;
import org.application.util.ResultMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

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
        request.getSession().setAttribute(ConstantString.USER_ID, Long.parseLong(userId));
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JsonUtil.toJson(ResultMessage.SUCCESS));
    }

}
