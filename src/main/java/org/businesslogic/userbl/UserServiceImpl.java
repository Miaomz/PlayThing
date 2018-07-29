package org.businesslogic.userbl;

import org.data.userdata.UserDAO;
import org.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.UserVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ResultMessage addUser(UserVO user) {
        List<UserPO> userPOList = userDAO.findAllUsers();
        boolean isDuplicate = false;    //indicates whether there is an existing user with the same name
        for (UserPO userPO : userPOList) {
            if (userPO.getName().equals(user.getUserName())){
                isDuplicate = true;
                break;
            }
        }

        if (isDuplicate){
            return ResultMessage.FAILURE;
        } else {
            return userDAO.addUser((UserPO) user.toPO());
        }
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        return userDAO.deleteUser(userId);
    }

    @Override
    public ResultMessage modifyUser(UserVO user) {
        return userDAO.modifyUser((UserPO) user.toPO());
    }

    @Override
    public UserVO findUserById(long userId) {
        return new UserVO(userDAO.findUserById(userId));
    }

    @Override
    public ResultMessage login(String password) {
        return userDAO.login(password);
    }

    @Override
    public ResultMessage changePassword(String oldPassword, String newPassword) {
        return userDAO.changePassword(oldPassword, newPassword);
    }
}
