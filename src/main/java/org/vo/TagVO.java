package org.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.po.PO;
import org.po.TagPO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVO implements VO{

    private long tagId;

    private String content;

    public TagVO(TagPO tagPO){
        this.tagId = tagPO.getTagId();
        this.content = tagPO.getContent();
    }

    @Override
    public PO toPO() {
        return new TagPO(tagId, false, content);
    }
}
