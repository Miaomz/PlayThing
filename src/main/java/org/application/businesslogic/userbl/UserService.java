package org.application.businesslogic.userbl;

import org.application.util.ResultMessage;
import org.application.vo.UserVO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public interface UserService {

    ResultMessage addUser(UserVO user);

    ResultMessage deleteUser(long userId);

    ResultMessage modifyUser(UserVO user);

    UserVO findUserById(long userId);

    ResultMessage login(String username, String password);

    ResultMessage changePassword(String username, String oldPassword, String newPassword);
}
