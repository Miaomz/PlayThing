package org.vo;

import org.po.CommentPO;
import org.po.PO;
import org.util.ClassType;

import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/8/3
 */
public class CommentVO implements VO{

    private long commentId;

    private long postId;

    private long replierId;

    private ClassType type = ClassType.COMMENT;

    /**
     * 这个是用户名 前端要展示 顺便就记录下来吧
     */
    private String replier;

    private LocalDateTime time;

    private String content;

    public CommentVO(long commentId, long postId, long replierId, String replier, LocalDateTime time, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.replierId = replierId;
        this.replier = replier;
        this.time = time;
        this.content = content;
    }

    public CommentVO(CommentPO commentPO){
        this.commentId = commentPO.getCommentId();
        this.postId = commentPO.getPostId();
        this.replierId = commentPO.getReplierId();
        this.replier = commentPO.getReplier();
        this.time = commentPO.getTime();
        this.content = commentPO.getContent();
    }

    @Override
    public PO toPO() {
        return new CommentPO(commentId, false, postId, replierId, replier, time, content);
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }
}
