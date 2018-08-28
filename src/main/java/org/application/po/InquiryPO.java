package org.application.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.util.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InquiryPO implements PO {
    @Id
    @GeneratedValue
    private long inquiryId;
    @Column(nullable = false)
    private long senderId;
    @Column(length = 2048)
    private String content;
    /**
     * -1 代表没有对应商品
     */
    private long commodityId = -1;
    private boolean isDeleted;
    private State state;
}
