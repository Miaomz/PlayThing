package org.data.shoppingdata;

import org.po.CommodityPO;
import org.po.InquiryPO;
import org.po.PurchasePO;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface ShoppingDAO {
    
    ResultMessage addCommodity(CommodityPO commodityPO);

    /**
     * 管理员根据用户发布的文化产品定制请求创建商品，并将对应定制请求关闭
     * @param commodityPO 新建商品信息（不含ID）
     * @param inquiryId 定制请求ID
     * @return  修改是否成功
     */
    ResultMessage addCommodityByInquiry(CommodityPO commodityPO, long inquiryId);

    ResultMessage deleteCommodity(long commodityId);

    ResultMessage modifyCommodity(CommodityPO commodityPO);

    CommodityPO findCommodityById(long commodityId);

    CommodityPO findCommodityByInquiry(long inquiryId);

    List<CommodityPO> findAllCommodities();

    ResultMessage buyCommodity(long cid, long uid, int quantity);

    ResultMessage addInquiry(InquiryPO inquiryPO);

    ResultMessage deleteInquiry(long inquiryId);

    ResultMessage modifyInquiry(InquiryPO inquiryPO);

    InquiryPO findInquiryById(long inquiryId);

    List<InquiryPO> findAllInquiries();

    List<PurchasePO> findAllPurchases();
}
