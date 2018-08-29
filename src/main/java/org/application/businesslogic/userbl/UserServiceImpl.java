package org.application.businesslogic.userbl;

import org.application.data.userdata.UserDAO;
import org.application.po.UserPO;
import org.application.util.ResultMessage;
import org.application.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public synchronized ResultMessage addUser(UserVO user) {
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
        UserPO userPO = userDAO.findUserById(userId);
        if (userPO != null && !userPO.isDeleted()) {
            return new UserVO(userPO);
        } else {
            return null;
        }
    }

    @Override
    public UserVO findUserByName(String username) {
        UserPO userPO = userDAO.findUserByName(username);
        if (userPO != null && !userPO.isDeleted()){
            return new UserVO(userPO);
        } else {
            return null;
        }
    }

    @Override
    public ResultMessage login(String username,String password) {
        return userDAO.login(username, password);
    }

    @Override
    public ResultMessage changePassword(String username, String oldPassword, String newPassword) {
        return userDAO.changePassword(username, oldPassword, newPassword);
    }
}
