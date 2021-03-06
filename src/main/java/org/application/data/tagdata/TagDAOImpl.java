package org.application.data.tagdata;

import org.application.po.TagPO;
import org.application.util.LoggerUtil;
import org.application.util.ResultMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class TagDAOImpl implements TagDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ResultMessage addTag(TagPO tag) {
        try {
            entityManager.persist(tag);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage modifyTag(TagPO tag) {
        try {
            entityManager.merge(tag);
            entityManager.flush();
        }catch (PersistenceException pe){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public TagPO findTagById(long tagId) {
        return entityManager.find(TagPO.class, tagId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TagPO> findAllTags() {
        return entityManager.createQuery("select t from TagPO t").getResultList();
    }
}
