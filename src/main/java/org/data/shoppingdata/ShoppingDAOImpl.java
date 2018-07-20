package org.data.shoppingdata;

import org.po.CommodityPO;
import org.po.InquiryPO;
import org.po.PurchasePO;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class ShoppingDAOImpl implements ShoppingDAO {
    @Override
    public ResultMessage addCommodity(CommodityPO commodityPO) {
        return null;
    }

    @Override
    public ResultMessage addCommodityByInquiry(CommodityPO commodityPO, long inquiryId) {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(long commodityId) {
        return null;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityPO commodityPO) {
        return null;
    }

    @Override
    public CommodityPO findCommodityById(long commodityId) {
        return null;
    }

    @Override
    public CommodityPO findCommodityByInquiry(long inquiryId) {
        return null;
    }

    @Override
    public List<CommodityPO> findAllCommodities() {
        return null;
    }

    @Override
    public ResultMessage buyCommodity(long cid, int quantity) {
        return null;
    }

    @Override
    public ResultMessage addInquiry(InquiryPO inquiryPO) {
        return null;
    }

    @Override
    public ResultMessage deleteInquiry(long inquiryId) {
        return null;
    }

    @Override
    public ResultMessage modifyInquiry(InquiryPO inquiryPO) {
        return null;
    }

    @Override
    public InquiryPO findInquiryById(long inquiryId) {
        return null;
    }

    @Override
    public List<InquiryPO> findAllInquiries() {
        return null;
    }

    @Override
    public List<PurchasePO> findAllPurchases() {
        return null;
    }
}
