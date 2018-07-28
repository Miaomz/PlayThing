package org.po;


import org.util.State;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class MessagePO implements PO {

    @Id
    @GeneratedValue
    private long messageId;

    private long userId;

    @OneToMany
    private List<TagPO> tags;

    private String title;

    private String content;

    private boolean isDeleted;

    private State state;

    public MessagePO() {
    }

    public MessagePO(long messageId, long userId, List<TagPO> tags, String title, String content, boolean isDeleted, State state) {
        this.messageId = messageId;
        this.userId = userId;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
        this.state = state;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<TagPO> getTags() {
        return tags;
    }

    public void setTags(List<TagPO> tags) {
        this.tags = tags;
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
