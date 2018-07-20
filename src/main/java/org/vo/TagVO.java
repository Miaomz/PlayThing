package org.vo;

import org.po.PO;
import org.po.TagPO;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class TagVO implements VO{

    private long tagId;

    private String content;

    public TagVO(long tagId, String content) {
        this.tagId = tagId;
        this.content = content;
    }

    public TagVO(TagPO tagPO){
        this.tagId = tagPO.getTagId();
        this.content = tagPO.getContent();
    }

    @Override
    public PO toPO() {
        return new TagPO(tagId, false, content);
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
}
