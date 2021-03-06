package org.application.data.userdata;

import org.application.po.UserPO;
import org.application.util.ResultMessage;

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

    UserPO findUserByName(String username);

    List<UserPO> findAllUsers();

    ResultMessage login(String username, String password);

    ResultMessage changePassword(String username, String oldPassword, String newPassword);
}
