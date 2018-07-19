package org.businesslogic.userbl;

import org.util.ResultMessage;
import org.vo.UserVO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public interface UserService {

    ResultMessage addUser(UserVO user);

    ResultMessage deleteUser(long userId);

    ResultMessage modifyUser(UserVO user);

    UserVO findUserById(long userId);

    ResultMessage login(String password);

    ResultMessage changePassword(String oldPassword, String newPassword);
}
