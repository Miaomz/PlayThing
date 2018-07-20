package org.data.userdata;

import org.po.UserPO;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public ResultMessage addUser(UserPO user) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        return null;
    }

    @Override
    public ResultMessage modifyUser(UserPO user) {
        return null;
    }

    @Override
    public UserPO findUserById(long userId) {
        return null;
    }

    @Override
    public ResultMessage login(String password) {
        return null;
    }

    @Override
    public ResultMessage changePassword(String oldPassword, String newPassword) {
        return null;
    }
}
