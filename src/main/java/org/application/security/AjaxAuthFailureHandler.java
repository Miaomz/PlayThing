package org.application.security;

import org.application.util.JsonUtil;
import org.application.util.ResultMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王川源
 */
public class AjaxAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        //ResultMessage message = ResultMessage.valueOf(exception.getMessage());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JsonUtil.toJson(ResultMessage.FAILURE));
    }

}
