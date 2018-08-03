package org.businesslogic.shoppingbl;

import org.util.ResultMessage;
import org.vo.CommodityVO;
import org.vo.InquiryVO;
import org.vo.PurchaseVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public interface ShoppingService {

    ResultMessage addCommodity(CommodityVO commodityVO);

    /**
     * 管理员根据用户发布的文化产品定制请求创建商品，并将对应定制请求关闭
     * @param commodityVO 新建商品信息（不含ID）
     * @param inquiryId 定制请求ID
     * @return  修改是否成功
     */
    ResultMessage addCommodityByInquiry(CommodityVO commodityVO, long inquiryId);

    ResultMessage deleteCommodity(long commodityId);

    ResultMessage modifyCommodity(CommodityVO commodityVO);

    CommodityVO findCommodityById(long commodityId);

    CommodityVO findCommodityByInquiry(long inquiryId);

    List<CommodityVO> findAllCommodities();

    ResultMessage buyCommodity(long cid, long uid, int quantity);

    ResultMessage addInquiry(InquiryVO inquiryVO);

    ResultMessage deleteInquiry(long inquiryId);

    ResultMessage modifyInquiry(InquiryVO inquiryVO);

    InquiryVO findInquiryById(long inquiryId);

    List<InquiryVO> findAllInquiries();

    List<PurchaseVO> findAllPurchases();
}
