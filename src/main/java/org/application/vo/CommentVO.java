package org.application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.po.CommentPO;
import org.application.po.PO;
import org.application.util.ClassType;

import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/8/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO implements VO{

    private long commentId;

    private long postId;

    private long replierId;

    /**
     * 这个是用户名 前端要展示 顺便就记录下来吧
     */
    private String replier;

    private LocalDateTime time;

    private String content;

    private ClassType type = ClassType.COMMENT;


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

}
