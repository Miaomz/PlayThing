package org.application.data.tagdata;

import org.application.po.TagPO;
import org.application.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface TagDAO {

    ResultMessage addTag(TagPO tag);

    ResultMessage modifyTag(TagPO tag);

    TagPO findTagById(long tagId);

    List<TagPO> findAllTags();
}
