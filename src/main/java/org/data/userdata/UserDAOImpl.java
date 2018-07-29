package org.data.userdata;

import org.po.UserPO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.util.LoggerUtil;
import org.util.ResultMessage;

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
            entityManager.persist(user);
        } catch (PersistenceException e){
            LoggerUtil.getLogger().info(e);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
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
    public ResultMessage modifyUser(UserPO user) {
        try {
            entityManager.merge(user);
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
    public List<UserPO> findAllUsers() {
        Query query = entityManager.createQuery("select u from UserPO u");
        return query.getResultList();
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
