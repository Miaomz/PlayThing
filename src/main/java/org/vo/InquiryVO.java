package org.vo;

import org.po.InquiryPO;
import org.po.PO;
import org.util.State;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class InquiryVO implements VO {
    private long inquiryId;
    private long senderId;
    private String content;
    private long commodityId;
    private State state;

    public InquiryVO(long inquiryId, long senderId, String content, long commodityId, State state) {
        this.inquiryId = inquiryId;
        this.senderId = senderId;
        this.content = content;
        this.commodityId = commodityId;
        this.state = state;
    }

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

    public long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
}
