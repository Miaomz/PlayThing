package org.vo;

import org.po.PO;
import org.po.PrivateMessagePO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class PrivateMessageVO implements VO{

    private long pmId;

    private long senderId;

    private long receiverId;

    private String title;

    private String content;

    public PrivateMessageVO(long pmId, long senderId, long receiverId, String title, String content) {
        this.pmId = pmId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.title = title;
        this.content = content;
    }

    public PrivateMessageVO(PrivateMessagePO privateMessagePO){
        this.pmId = privateMessagePO.getPmId();
        this.senderId = privateMessagePO.getSenderId();
        this.receiverId = privateMessagePO.getReceiverId();
        this.title = privateMessagePO.getTitle();
        this.content = privateMessagePO.getContent();
    }

    @Override
    public PO toPO() {
        return new PrivateMessagePO(pmId, senderId, receiverId, false, title, content);
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
