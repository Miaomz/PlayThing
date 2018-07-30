package org.po;

import org.util.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class InquiryPO implements PO {
    @Id
    @GeneratedValue
    private long inquiryId;
    private long senderId;
    @Column(length = 2048)
    private String content;
    private boolean isDeleted;
    private State state;

    public InquiryPO() {}

    public InquiryPO(long inquiryId, long senderId, String content, boolean isDeleted, State state) {
        this.inquiryId = inquiryId;
        this.senderId = senderId;
        this.content = content;
        this.isDeleted = isDeleted;
        this.state = state;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
