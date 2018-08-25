package org.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.po.PO;
import org.po.PurchasePO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseVO implements VO{
    private long pid;
    private long cid;
    private long buyerId;
    private int quantity;

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
}
