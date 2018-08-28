package org.application.security;

import org.application.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 提供UserDetail的具体验证方式
 * 参见https://blog.csdn.net/tzdwsy/article/details/50738043
 * @author 王川源
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    /**
     * 自定义验证方式
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();
        MyUserDetails user = (MyUserDetails)myUserDetailsService.loadUserByUsername(userId);
        if(user == null)
            throw new UsernameNotFoundException(ResultMessage.INEXISTENCE.toString());

        //MD5加密
        password = MD5Encrypt.md5(password);
        if(!password.equals(user.getPassword()))
            throw new BadCredentialsException(ResultMessage.WRONG_PASS.toString());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
