package org.vo;

import org.po.MessagePO;
import org.po.PO;
import org.po.TagPO;
import org.util.State;
import org.util.TransUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class MessageVO implements VO {

    private long messageId;

    private long userId;

    private List<TagVO> tags;

    private String title;

    private String content;

    private State status;

    public MessageVO(long messageId, long userId, List<TagVO> tags, String title, String content, State status) {
        this.messageId = messageId;
        this.userId = userId;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public MessageVO(MessagePO messagePO){
        this.messageId = messagePO.getMessageId();
        this.userId = messagePO.getUserId();
        this.title = messagePO.getTitle();
        this.content = messagePO.getContent();
        this.status = messagePO.getStatus();

        List<TagPO> tagPOs = messagePO.getTags();
        this.tags = new ArrayList<>(tagPOs.size());
        tagPOs.forEach(tagPO -> tags.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new MessagePO(messageId, userId, TransUtil.toPOList(tags), title, content, false, status);
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

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
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

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}
