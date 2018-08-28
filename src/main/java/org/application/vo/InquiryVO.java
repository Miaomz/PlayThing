package org.application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.po.InquiryPO;
import org.application.po.PO;
import org.application.util.State;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryVO implements VO {
    private long inquiryId;
    private long senderId;
    private String content;
    private long commodityId;
    private State state;

    public InquiryVO(InquiryPO inquiryPO) {
        this.inquiryId = inquiryPO.getInquiryId();
        this.senderId = inquiryPO.getSenderId();
        this.content = inquiryPO.getContent();
        this.commodityId = inquiryPO.getCommodityId();
        this.state = inquiryPO.getState();
    }

    @Override
    public PO toPO() {
        return new InquiryPO(inquiryId, senderId, content, commodityId,false, state);
    }
}
