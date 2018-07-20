package org.data.tagdata;

import org.po.TagPO;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface TagDAO {

    ResultMessage addTag(TagPO tag);

    ResultMessage modifyTag(TagPO tag);

    List<TagPO> findTagById(long tagId);
}
