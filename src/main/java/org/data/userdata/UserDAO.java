package org.data.userdata;

import org.po.UserPO;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface UserDAO {

    ResultMessage addUser(UserPO user);

    ResultMessage deleteUser(long userId);

    ResultMessage modifyUser(UserPO user);

    UserPO findUserById(long userId);

    List<UserPO> findAllUsers();

    ResultMessage login(String password);

    ResultMessage changePassword(String oldPassword, String newPassword);
}
