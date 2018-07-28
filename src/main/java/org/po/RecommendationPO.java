package org.po;

import javax.persistence.*;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
@Table(name = "recommendation")
public class RecommendationPO implements PO{

    @Id
    @GeneratedValue
    private long rid;

    @Column(name = "content", length = 1024)
    private String content;

    private boolean isDeleted;

    @OneToMany
    private List<TagPO> tags;

    public RecommendationPO() {
    }

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
