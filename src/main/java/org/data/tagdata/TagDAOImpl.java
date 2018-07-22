package org.data.tagdata;

import org.po.TagPO;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class TagDAOImpl implements TagDAO {
    @Override
    public ResultMessage addTag(TagPO tag) {
        return null;
    }

    @Override
    public ResultMessage modifyTag(TagPO tag) {
        return null;
    }

    @Override
    public TagPO findTagById(long tagId) {
        return null;
    }
}
