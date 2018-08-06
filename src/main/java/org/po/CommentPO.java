package org.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/8/3
 */
@Entity
public class CommentPO implements PO{

    @Id
    private long commentId;

    private boolean isDeleted;

    @Column(nullable = false)
    private long postId;

    @Column(nullable = false)
    private long replierId;

    /**
     * 这个是用户名 前端要展示 顺便就记录下来吧
     */
    private String replier;

    private LocalDateTime time;

    @Column(length = 2048)
    private String content;

    public CommentPO() {}

    public CommentPO(long commentId, boolean isDeleted, long postId, long replierId, String replier, LocalDateTime time, String content) {
        this.commentId = commentId;
        this.isDeleted = isDeleted;
        this.postId = postId;
        this.replierId = replierId;
        this.replier = replier;
        this.time = time;
        this.content = content;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getReplierId() {
        return replierId;
    }

    public void setReplierId(long replierId) {
        this.replierId = replierId;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
