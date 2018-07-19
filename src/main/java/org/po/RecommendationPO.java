package org.po;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class RecommendationPO implements PO{
    private long rid;
    private String content;
    private boolean isDeleted;
    private List<TagPO> tags;

    public RecommendationPO(long rid, String content, boolean isDeleted, List<TagPO> tags) {
        this.rid = rid;
        this.content = content;
        this.isDeleted = isDeleted;
        this.tags = tags;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
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

    public List<TagPO> getTags() {
        return tags;
    }

    public void setTags(List<TagPO> tags) {
        this.tags = tags;
    }
}
