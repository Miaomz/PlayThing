package org.po;

import javax.persistence.*;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
@Table(name = "tag")
public class TagPO implements PO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tagId;

    private boolean isDeleted;

    @Column(name = "content", length = 64)
    private String content;

    public TagPO() {}

    public TagPO(long tagId, boolean isDeleted, String content) {
        this.tagId = tagId;
        this.isDeleted = isDeleted;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
