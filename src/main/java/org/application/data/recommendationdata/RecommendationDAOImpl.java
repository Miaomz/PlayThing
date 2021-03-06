package org.application.data.recommendationdata;


import org.application.po.RecommendationPO;
import org.application.util.LoggerUtil;
import org.application.util.ResultMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class RecommendationDAOImpl implements RecommendationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResultMessage addRecommendation(RecommendationPO recommendationPO) {
        try {
            entityManager.persist(recommendationPO);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage deleteRecommendation(long recommendationId) {
        try {
            Query query = entityManager.createQuery("update RecommendationPO r set r.isDeleted = true where r.rid = :deleteId");
            query.setParameter("deleteId", recommendationId);
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
    public ResultMessage modifyRecommendation(RecommendationPO recommendationPO) {
        try {
            entityManager.merge(recommendationPO);
            entityManager.flush();
        }catch (PersistenceException pe){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public RecommendationPO findRecommendationById(long rid) {
        return entityManager.find(RecommendationPO.class, rid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RecommendationPO> findAllRecommendations() {
        try {
            Query query = entityManager.createQuery("select r from RecommendationPO r");
            return query.getResultList();
        } catch (PersistenceException e){
            LoggerUtil.getLogger().info(e);
            return new ArrayList<>();
        }
    }
}
