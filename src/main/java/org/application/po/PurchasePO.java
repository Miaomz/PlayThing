package org.application.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchasePO implements PO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;

    private long cid;

    private long buyerId;

    private int quantity;

    private boolean isDeleted;

}
