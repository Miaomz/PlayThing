package org.data.shoppingdata;

import org.po.CommodityPO;
import org.po.InquiryPO;
import org.po.PurchasePO;
import org.po.UserPO;
import org.springframework.data.jpa.repository.Modifying;
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
public class ShoppingDAOImpl implements ShoppingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ResultMessage addCommodity(CommodityPO commodityPO) {
        try {
            entityManager.persist(commodityPO);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    public ResultMessage addCommodityByInquiry(CommodityPO commodityPO, long inquiryId) {
        try {
            entityManager.persist(commodityPO);
            Query query = entityManager.createQuery("update InquiryPO i set i.commodityId = :cid where i.inquiryId = :iid");
            query.setParameter("cid", commodityPO.getCid());
            query.setParameter("iid", inquiryId);
            query.executeUpdate();
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage deleteCommodity(long commodityId) {
        Query query = entityManager.createQuery("update CommodityPO c set c.isDeleted = true where c.cid = :id");
        query.setParameter("id", commodityId);
        ResultMessage resultMessage = (query.executeUpdate() == 1) ? ResultMessage.SUCCESS: ResultMessage.FAILURE;
        entityManager.clear();
        return resultMessage;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage modifyCommodity(CommodityPO commodityPO) {
        try {
            entityManager.merge(commodityPO);
            entityManager.flush();
        } catch (PersistenceException e){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public CommodityPO findCommodityById(long commodityId) {
        return entityManager.find(CommodityPO.class, commodityId);
    }

    @Override
    public CommodityPO findCommodityByInquiry(long inquiryId) {
        InquiryPO inquiryPO = entityManager.find(InquiryPO.class, inquiryId);
        if (inquiryPO == null){
            return null;
        } else {
            return findCommodityById(inquiryPO.getCommodityId());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CommodityPO> findAllCommodities() {
        Query query = entityManager.createQuery("select c from CommodityPO c");
        return query.getResultList();
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage buyCommodity(long cid, long uid, int quantity) {
        CommodityPO commodityPO = entityManager.find(CommodityPO.class, cid);
        UserPO userPO = entityManager.find(UserPO.class, uid);
        if (commodityPO == null || userPO == null) {
            return ResultMessage.INEXISTENCE;
        }

        //商品存量不足或用户余额不足
        if (commodityPO.getRemainedQuantity() < quantity || commodityPO.getPrice()*quantity > userPO.getBalance()){
            return ResultMessage.FAILURE;
        }
        commodityPO.setRemainedQuantity(commodityPO.getRemainedQuantity() - quantity);
        userPO.setBalance(userPO.getBalance() - commodityPO.getPrice()*quantity);
        entityManager.clear();
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    public ResultMessage addInquiry(InquiryPO inquiryPO) {
        try {
            entityManager.persist(inquiryPO);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    @Transactional
    public ResultMessage deleteInquiry(long inquiryId) {
        Query query = entityManager.createQuery("update InquiryPO i set i.isDeleted = true where i.inquiryId = :iid");
        query.setParameter("iid", inquiryId);
        ResultMessage resultMessage = (query.executeUpdate() == 1)? ResultMessage.SUCCESS: ResultMessage.FAILURE;
        entityManager.clear();
        return resultMessage;
    }

    @Override
    @Transactional
    @Modifying
    public ResultMessage modifyInquiry(InquiryPO inquiryPO) {
        try {
            entityManager.merge(inquiryPO);
            entityManager.flush();
        } catch (PersistenceException e){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public InquiryPO findInquiryById(long inquiryId) {
        return entityManager.find(InquiryPO.class, inquiryId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<InquiryPO> findAllInquiries() {
        Query query = entityManager.createQuery("select i from InquiryPO i");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PurchasePO> findAllPurchases() {
        Query query = entityManager.createQuery("select p from PurchasePO p");
        return query.getResultList();
    }
}
