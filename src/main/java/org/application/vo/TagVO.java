package org.application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.po.PO;
import org.application.po.TagPO;

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
