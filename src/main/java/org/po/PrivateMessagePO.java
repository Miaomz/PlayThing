package org.po;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class PrivateMessagePO implements PO {

    private long pmId;

    private long senderId;

    private long receiverId;

    private boolean isDeleted;

    private String title;

    private String content;

    public PrivateMessagePO(long pmId, long senderId, long receiverId, boolean isDeleted, String title, String content) {
        this.pmId = pmId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.isDeleted = isDeleted;
        this.title = title;
        this.content = content;
    }

    public long getPmId() {
        return pmId;
    }

    public void setPmId(long pmId) {
        this.pmId = pmId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
