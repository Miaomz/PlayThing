package org.businesslogic.tagbl;

import org.util.ResultMessage;
import org.vo.TagVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public interface TagService {

    ResultMessage addTag(TagVO tag);

    ResultMessage modifyTag(TagVO tag);

    TagVO findTagById(long tagId);

    TagVO findTagByContent(String content);

    List<TagVO> findAllTags();
}
