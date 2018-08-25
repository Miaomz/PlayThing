package org.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationVO implements VO {
    private long rid;
    private String content;
    private List<TagVO> tags;

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
}
