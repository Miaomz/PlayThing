package org.vo;

import org.po.CommodityPO;
import org.po.PO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class CommodityVO implements VO{
    private long cid;
    private long vendorId;
    private double price;
    private int remainedQuantity;
    private String description;

    public CommodityVO(long cid, long vendorId, double price, int remainedQuantity, String description) {
        this.cid = cid;
        this.vendorId = vendorId;
        this.price = price;
        this.remainedQuantity = remainedQuantity;
        this.description = description;
    }

    @Override
    public PO toPO() {
        return new CommodityPO(cid, vendorId, false, price, remainedQuantity, description);
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
