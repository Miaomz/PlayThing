package org.businesslogic.shoppingbl;

import org.data.shoppingdata.ShoppingDAO;
import org.po.CommodityPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.CommodityVO;
import org.vo.InquiryVO;
import org.vo.PurchaseVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class ShoppingServiceImpl implements ShoppingService {

    private ShoppingDAO shoppingDAO;

    @Autowired
    public void setShoppingDAO(ShoppingDAO shoppingDAO) {
        this.shoppingDAO = shoppingDAO;
    }

    @Override
    public ResultMessage addCommodity(CommodityVO commodityVO) {
        return shoppingDAO.addCommodity((CommodityPO) commodityVO.toPO());
    }

    @Override
    public ResultMessage addCommodityByInquiry(CommodityVO commodityVO, long inquiryId) {
        return shoppingDAO.addCommodityByInquiry((CommodityPO) commodityVO.toPO(), inquiryId);
    }

    @Override
    public ResultMessage deleteCommodity(long commodityId) {
        return shoppingDAO.deleteCommodity(commodityId);
    }

    @Override
    public ResultMessage modifyCommodity(CommodityVO commodityVO) {
        return shoppingDAO.modifyCommodity((CommodityPO) commodityVO.toPO());
    }

    @Override
    public CommodityVO findCommodityById(long commodityId) {
        return new CommodityVO(shoppingDAO.findCommodityById(commodityId));
    }

    @Override
    public CommodityVO findCommodityByInquiry(long inquiryId) {
        return new CommodityVO(shoppingDAO.findCommodityByInquiry(inquiryId));
    }

    @Override
    public List<CommodityVO> findAllCommodities() {
        return null;
    }

    @Override
    public ResultMessage buyCommodity(long cid, int quantity) {
        return null;
    }

    @Override
    public ResultMessage addInquiry(InquiryVO inquiryVO) {
        return null;
    }

    @Override
    public ResultMessage deleteInquiry(long inquiryId) {
        return null;
    }

    @Override
    public ResultMessage modifyInquiry(InquiryVO inquiryVO) {
        return null;
    }

    @Override
    public InquiryVO findInquiryById(long inquiryId) {
        return null;
    }

    @Override
    public List<InquiryVO> findAllInquiries() {
        return null;
    }

    @Override
    public List<PurchaseVO> findAllPurchases() {
        return null;
    }
}
