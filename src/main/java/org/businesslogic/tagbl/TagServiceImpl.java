package org.businesslogic.tagbl;

import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.TagVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class TagServiceImpl implements TagService{

    @Override
    public ResultMessage addTag(TagVO tag) {
        return null;
    }

    @Override
    public ResultMessage modifyTag(TagVO tag) {
        return null;
    }

    @Override
    public List<TagVO> findTagById(long tagId) {
        return null;
    }
}
