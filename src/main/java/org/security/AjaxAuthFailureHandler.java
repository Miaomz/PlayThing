package org.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.util.JsonUtil;
import org.util.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王川源
 */
public class AjaxAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ResultMessage message = ResultMessage.valueOf(exception.getMessage());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JsonUtil.toJson(message));
    }

}
