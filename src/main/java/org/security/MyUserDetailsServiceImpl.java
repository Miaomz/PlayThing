package org.security;

import org.data.userdata.UserDAO;
import org.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;

/**
 * 实现获取UserDetails的方式
 * 参见https://blog.csdn.net/tzdwsy/article/details/50738043
 * @author 王川源
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws AuthenticationException {
        UserPO user = userDAO.findUserById(Long.parseLong(userId));
        if(user == null || user.isDeleted())
            throw new UsernameNotFoundException(ResultMessage.INEXISTENCE.toString());
        else {
            return new MyUserDetails(user);
        }
    }

}
