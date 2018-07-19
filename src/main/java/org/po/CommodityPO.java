package org.po;

import javax.persistence.Entity;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class CommodityPO implements PO{
    private long cid;
    private long vendorId;
    private boolean isDeleted;
    private double price;
    private int remainedQuantity;
    private String description;

    public CommodityPO(long cid, long vendorId, boolean isDeleted, double price, int remainedQuantity, String description) {
        this.cid = cid;
        this.vendorId = vendorId;
        this.isDeleted = isDeleted;
        this.price = price;
        this.remainedQuantity = remainedQuantity;
        this.description = description;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainedQuantity() {
        return remainedQuantity;
    }

    public void setRemainedQuantity(int remainedQuantity) {
        this.remainedQuantity = remainedQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
