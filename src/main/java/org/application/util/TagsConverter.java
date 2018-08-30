package org.application.util;

import org.application.po.TagPO;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/8/30
 */
public class TagsConverter implements AttributeConverter<List<TagPO>, String>{

    @Override
    public String convertToDatabaseColumn(List<TagPO> tagPOS) {
        return JsonUtil.toJson(tagPOS);
    }

    @Override
    public List<TagPO> convertToEntityAttribute(String s) {
        return JsonUtil.toArray(s, TagPO.class);
    }
}
