package org.data.recommendationdata;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.po.RecommendationPO;
import org.po.TagPO;
import org.springframework.stereotype.Component;
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
public class RecommendationDAOImpl implements RecommendationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResultMessage addRecommendation(RecommendationPO recommendationPO) {
        try {
            entityManager.persist(recommendationPO);
        }catch (PersistenceException pe){
            LogManager.getLogger().log(Level.INFO, "persist", pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteRecommendation(long recommendationId) {
        try {
            Query query = entityManager.createQuery("update RecommendationPO r set r.isDeleted = false where r.rid = :deleteId");
            query.setParameter("deleteId", recommendationId);
            query.executeUpdate();
            entityManager.clear();
        } catch (PersistenceException e){
            LogManager.getLogger().log(Level.INFO, "persist", e);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyRecommendation(RecommendationPO recommendationPO) {
        return null;
    }

    @Override
    public RecommendationPO findRecommendationById(long rid) {
        return entityManager.find(RecommendationPO.class, rid);
    }

    @Override
    public List<RecommendationPO> findRecommendationByTag(List<TagPO> tags) {

        return null;
    }
}
