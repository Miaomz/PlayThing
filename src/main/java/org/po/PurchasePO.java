package org.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class PurchasePO implements PO {

    @Id
    @GeneratedValue
    private long purchaseId;

    private long cid;

    private long buyerId;

    private int quantity;

    private boolean isDeleted;

    public PurchasePO() {
    }

    public PurchasePO(long purchaseId, long cid, long buyerId, int quantity, boolean isDeleted) {
        this.purchaseId = purchaseId;
        this.cid = cid;
        this.buyerId = buyerId;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
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
