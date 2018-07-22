package org.data.tagdata;

import org.po.TagPO;
import org.util.ResultMessage;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface TagDAO {

    ResultMessage addTag(TagPO tag);

    ResultMessage modifyTag(TagPO tag);

    TagPO findTagById(long tagId);
}
