package org.application.data.userdata;

import org.application.po.UserPO;
import org.application.security.MD5Encrypt;
import org.application.util.LoggerUtil;
import org.application.util.ResultMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ResultMessage addUser(UserPO user) {
        try {
            user.setPassword(MD5Encrypt.md5(user.getPassword()));
            entityManager.persist(user);
        } catch (PersistenceException e){
            LoggerUtil.getLogger().info(e);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage deleteUser(long userId) {
        try {
            Query query = entityManager.createQuery("update UserPO u set u.isDeleted = true where u.userId = :uid");
            query.setParameter("uid", userId);
            query.executeUpdate();
            entityManager.clear();
        } catch (PersistenceException e){
            LoggerUtil.getLogger().info(e);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage modifyUser(UserPO user) {
        try {
            entityManager.merge(user);//when we modify users, we can't just encrypt passwords
            entityManager.flush();
        } catch (PersistenceException e){
            LoggerUtil.getLogger().info(e);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public UserPO findUserById(long userId) {
        return entityManager.find(UserPO.class, userId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserPO findUserByName(String username) {
        Query query = entityManager.createQuery("select u from UserPO u where u.name = :uname");
        query.setParameter("uname", username);
        List<UserPO> userPOS = query.getResultList();
        if (userPOS == null || userPOS.isEmpty()){
            return null;
        } else {
            return userPOS.get(0);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserPO> findAllUsers() {
        Query query = entityManager.createQuery("select u from UserPO u");
        return query.getResultList();
    }

    @Override
    public ResultMessage login(String username, String password) {
        UserPO userPO = findUserByName(username);
        if (userPO == null){
            return ResultMessage.INEXISTENCE;
        } else if (MD5Encrypt.md5(password).equals(userPO.getPassword())){
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage changePassword(String username, String oldPassword, String newPassword) {
        if (login(username, oldPassword) == ResultMessage.SUCCESS){
            UserPO userPO = findUserByName(username);
            userPO.setPassword(MD5Encrypt.md5(newPassword));
            return modifyUser(userPO);
        } else {
            return ResultMessage.FAILURE;
        }
    }
}
