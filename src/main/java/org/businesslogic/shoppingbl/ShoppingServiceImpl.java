package org.businesslogic.shoppingbl;

import org.data.shoppingdata.ShoppingDAO;
import org.po.CommodityPO;
import org.po.InquiryPO;
import org.po.PurchasePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.CommodityVO;
import org.vo.InquiryVO;
import org.vo.PurchaseVO;

import java.util.ArrayList;
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
        List<CommodityPO> commodityPOS = shoppingDAO.findAllCommodities();
        List<CommodityVO> commodityVOS = new ArrayList<>(commodityPOS.size());
        commodityPOS.forEach(commodityPO -> commodityVOS.add(new CommodityVO(commodityPO)));
        return commodityVOS;
    }

    @Override
    public ResultMessage buyCommodity(long cid, int quantity) {
        return shoppingDAO.buyCommodity(cid, quantity);
    }

    @Override
    public ResultMessage addInquiry(InquiryVO inquiryVO) {
        return shoppingDAO.addInquiry((InquiryPO) inquiryVO.toPO());
    }

    @Override
    public ResultMessage deleteInquiry(long inquiryId) {
        return shoppingDAO.deleteInquiry(inquiryId);
    }

    @Override
    public ResultMessage modifyInquiry(InquiryVO inquiryVO) {
        return shoppingDAO.modifyInquiry((InquiryPO) inquiryVO.toPO());
    }

    @Override
    public InquiryVO findInquiryById(long inquiryId) {
        return new InquiryVO(shoppingDAO.findInquiryById(inquiryId));
    }

    @Override
    public List<InquiryVO> findAllInquiries() {
        List<InquiryPO> inquiryPOS = shoppingDAO.findAllInquiries();
        List<InquiryVO> inquiryVOS = new ArrayList<>(inquiryPOS.size());
        inquiryPOS.forEach(inquiryPO -> inquiryVOS.add(new InquiryVO(inquiryPO)));
        return inquiryVOS;
    }

    @Override
    public List<PurchaseVO> findAllPurchases() {
        List<PurchasePO> purchasePOS = shoppingDAO.findAllPurchases();
        List<PurchaseVO> purchaseVOS = new ArrayList<>(purchasePOS.size());
        purchasePOS.forEach(purchasePO -> purchaseVOS.add(new PurchaseVO(purchasePO)));
        return purchaseVOS;
    }
}
