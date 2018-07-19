package org.po;

import javax.persistence.Entity;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class TagPO implements PO {

    private long tagId;

    private boolean isDeleted;

    private String content;

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
