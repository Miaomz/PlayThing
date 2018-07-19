package org.po;

import javax.persistence.Entity;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class PurchasePO implements PO {
    private long cid;
    private long buyerId;
    private int quantity;
    private boolean isDeleted;

    public PurchasePO(long cid, long buyerId, int quantity, boolean isDeleted) {
        this.cid = cid;
        this.buyerId = buyerId;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
