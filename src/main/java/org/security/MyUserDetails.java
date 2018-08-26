package org.security;

import org.po.UserPO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 自定义的用户认证实体
 * 参见https://blog.csdn.net/tzdwsy/article/details/50738043
 * @author 王川源
 */
public class MyUserDetails extends UserPO implements UserDetails {

    public MyUserDetails(UserPO user) {
        super(user.getUserId(), user.getName(), user.getPassword(),
                user.getLocation(), user.getPhone(), user.getMail(), user.getDisplay(),
                user.getRole(), user.isDeleted(), user.getBalance(), user.getTags());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(super.getRole());
    }

    @Override
    public String getUsername() {
        return Long.toString(super.getUserId());
    }

    @Override
    public String getPassword(){
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof MyUserDetails && getUsername().equals(((MyUserDetails) rhs).getUsername());
    }

    @Override
    public int hashCode(){
        return getUsername().hashCode();
    }
}
