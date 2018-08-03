package org.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class PrivateMessagePO implements PO {

    @Id
    @GeneratedValue
    private long pmId;

    private long senderId;

    private long receiverId;

    private boolean isDeleted;

    private String title;

    private LocalDateTime time;

    private boolean isChecked;

    @Column(length = 2048)
    private String content;

    public PrivateMessagePO(long pmId, long senderId, long receiverId, boolean isDeleted, String title, String content, LocalDateTime time, boolean isChecked) {
        this.pmId = pmId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.isDeleted = isDeleted;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isChecked = isChecked;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
