package org.vo;

import org.po.PO;
import org.po.PurchasePO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class PurchaseVO implements VO{
    private long cid;
    private long buyerId;
    private int quantity;

    public PurchaseVO(long cid, long buyerId, int quantity) {
        this.cid = cid;
        this.buyerId = buyerId;
        this.quantity = quantity;
    }

    @Override
    public PO toPO() {
        return new PurchasePO(cid, buyerId, quantity, false);
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
