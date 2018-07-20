package org.vo;

import org.po.PO;
import org.po.RecommendationPO;
import org.po.TagPO;
import org.util.TransUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class RecommendationVO implements VO {
    private long rid;
    private String content;
    private List<TagVO> tags;

    public RecommendationVO(long rid, String content, List<TagVO> tags) {
        this.rid = rid;
        this.content = content;
        this.tags = tags;
    }

    public RecommendationVO(RecommendationPO recommendationPO) {
        this.rid = recommendationPO.getRid();
        this.content = recommendationPO.getContent();

        List<TagPO> tagPOs = recommendationPO.getTags();
        this.tags = new ArrayList<>(tagPOs.size());
        tagPOs.forEach(tagPO -> tags.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new RecommendationPO(rid, content, false, TransUtil.toPOList(tags));
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

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }
}
