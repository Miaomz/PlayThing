package org.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/8/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
