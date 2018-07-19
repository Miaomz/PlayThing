package org.businesslogic.userbl;

import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.UserVO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class UserServiceImpl implements UserService {

    @Override
    public ResultMessage addUser(UserVO user) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        return null;
    }

    @Override
    public ResultMessage modifyUser(UserVO user) {
        return null;
    }

    @Override
    public UserVO findUserById(long userId) {
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
