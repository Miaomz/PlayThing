package org.vo;

import org.po.PO;
import org.po.PurchasePO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class PurchaseVO implements VO{
    private long pid;
    private long cid;
    private long buyerId;
    private int quantity;

    public PurchaseVO(long pid, long cid, long buyerId, int quantity) {
        this.pid = pid;
        this.cid = cid;
        this.buyerId = buyerId;
        this.quantity = quantity;
    }

    public PurchaseVO(PurchasePO purchasePO) {
        this.pid = purchasePO.getPurchaseId();
        this.cid = purchasePO.getCid();
        this.buyerId = purchasePO.getBuyerId();
        this.quantity = purchasePO.getQuantity();
    }

    @Override
    public PO toPO() {
        return new PurchasePO(pid, cid, buyerId, quantity, false);
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
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
