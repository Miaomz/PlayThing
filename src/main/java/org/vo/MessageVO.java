package org.vo;

import org.po.MessagePO;
import org.po.PO;
import org.po.TagPO;
import org.util.PostType;
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

    private long writer;

    private List<TagVO> tags;

    private PostType postType;

    private List<String> pic;

    private String video;

    private List<String> covers;

    private String title;

    private String content;

    private State status;

    public MessageVO(long messageId, long writer, List<TagVO> tags, PostType postType, List<String> pic, String video, List<String> covers, String title, String content, State status) {
        this.messageId = messageId;
        this.writer = writer;
        this.tags = tags;
        this.postType = postType;
        this.pic = pic;
        this.video = video;
        this.covers = covers;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public MessageVO(MessagePO messagePO){
        this.messageId = messagePO.getMessageId();
        this.writer = messagePO.getUserId();
        this.postType = messagePO.getPostType();
        this.pic = messagePO.getPic();
        this.video = messagePO.getVideo();
        this.covers = messagePO.getCovers();
        this.title = messagePO.getTitle();
        this.content = messagePO.getContent();
        this.status = messagePO.getStatus();

        List<TagPO> tagPOs = messagePO.getTags();
        this.tags = new ArrayList<>(tagPOs.size());
        tagPOs.forEach(tagPO -> tags.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new MessagePO(messageId, writer, TransUtil.toPOList(tags), postType, pic, video, covers, title, content, false, status);
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getWriter() {
        return writer;
    }

    public void setWriter(long writer) {
        this.writer = writer;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }

    public List<String> getCovers() {
        return covers;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setCovers(List<String> covers) {
        this.covers = covers;
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
