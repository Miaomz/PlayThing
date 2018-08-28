package org.application.businesslogic.shoppingbl;

import org.application.data.shoppingdata.ShoppingDAO;
import org.application.po.CommodityPO;
import org.application.po.InquiryPO;
import org.application.po.PurchasePO;
import org.application.util.ResultMessage;
import org.application.util.TransUtil;
import org.application.vo.CommodityVO;
import org.application.vo.InquiryVO;
import org.application.vo.PurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        CommodityPO commodityPO = shoppingDAO.findCommodityById(commodityId);
        if (commodityPO != null && !commodityPO.isDeleted()){
            return new CommodityVO(commodityPO);
        } else {
            return null;
        }
    }

    @Override
    public CommodityVO findCommodityByInquiry(long inquiryId) {
        CommodityPO commodityPO = shoppingDAO.findCommodityByInquiry(inquiryId);
        if (commodityPO != null && !commodityPO.isDeleted()){
            return new CommodityVO(commodityPO);
        } else {
            return null;
        }
    }

    @Override
    public List<CommodityVO> findAllCommodities() {
        List<CommodityPO> commodityPOS = shoppingDAO.findAllCommodities();
        TransUtil.removeDeleted(commodityPOS);

        List<CommodityVO> commodityVOS = new ArrayList<>(commodityPOS.size());
        commodityPOS.forEach(commodityPO -> commodityVOS.add(new CommodityVO(commodityPO)));
        return commodityVOS;
    }

    @Override
    public ResultMessage buyCommodity(long cid, long uid, int quantity) {
        return shoppingDAO.buyCommodity(cid, uid, quantity);
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
        InquiryPO inquiryPO = shoppingDAO.findInquiryById(inquiryId);
        if (inquiryPO != null && !inquiryPO.isDeleted()){
            return new InquiryVO(inquiryPO);
        } else {
            return null;
        }
    }

    @Override
    public List<InquiryVO> findAllInquiries() {
        List<InquiryPO> inquiryPOS = shoppingDAO.findAllInquiries();
        TransUtil.removeDeleted(inquiryPOS);

        List<InquiryVO> inquiryVOS = new ArrayList<>(inquiryPOS.size());
        inquiryPOS.forEach(inquiryPO -> inquiryVOS.add(new InquiryVO(inquiryPO)));
        return inquiryVOS;
    }

    @Override
    public List<PurchaseVO> findAllPurchases() {
        List<PurchasePO> purchasePOS = shoppingDAO.findAllPurchases();
        TransUtil.removeDeleted(purchasePOS);

        List<PurchaseVO> purchaseVOS = new ArrayList<>(purchasePOS.size());
        purchasePOS.forEach(purchasePO -> purchaseVOS.add(new PurchaseVO(purchasePO)));
        return purchaseVOS;
    }
}
