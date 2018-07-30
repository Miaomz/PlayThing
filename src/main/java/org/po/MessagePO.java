package org.po;


import org.util.PostType;
import org.util.State;

import javax.persistence.*;
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

    @OneToMany(targetEntity = TagPO.class)
    private List<TagPO> tags;

    /**
     * 标示文本的嵌入图片还是视频
     */
    private PostType postType;

    /**
     * 嵌入图片的url列表；若postType为video，则无意义
     */
    @ElementCollection
    private List<String> pic;

    /**
     * 嵌入视频的url；若postType为pic，则无意义
     */
    private String video;

    @ElementCollection
    private List<String> covers;

    private String title;

    @Column(length = 2048)
    private String content;

    private boolean isDeleted;

    private State status;

    public MessagePO() {}

    public MessagePO(long messageId, long userId, List<TagPO> tags, PostType postType, List<String> pic, String video, List<String> covers, String title, String content, boolean isDeleted, State status) {
        this.messageId = messageId;
        this.userId = userId;
        this.tags = tags;
        this.postType = postType;
        this.pic = pic;
        this.video = video;
        this.covers = covers;
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
        this.status = status;
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

    public List<String> getCovers() {
        return covers;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}
