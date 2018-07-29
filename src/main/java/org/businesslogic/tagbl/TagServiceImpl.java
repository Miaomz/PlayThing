package org.businesslogic.tagbl;

import org.data.tagdata.TagDAO;
import org.po.TagPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.TagVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class TagServiceImpl implements TagService {

    private TagDAO tagDAO;

    @Override
    public TagVO findTagByContent(String content) {
        List<TagVO> tagVOS = findAllTags();
        for (TagVO tagVO : tagVOS) {
            if (tagVO.getContent().equals(content)){
                return tagVO;
            }
        }
        return null;
    }

    @Override
    public List<TagVO> findAllTags() {
        List<TagPO> tagPOS = tagDAO.findAllTags();
        List<TagVO> tagVOS = new ArrayList<>(tagPOS.size());
        tagPOS.forEach(tagPO -> tagVOS.add(new TagVO(tagPO)));
        return tagVOS;
    }

    @Autowired
    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public ResultMessage addTag(TagVO tag) {
        return tagDAO.addTag((TagPO) tag.toPO());
    }

    @Override
    public ResultMessage modifyTag(TagVO tag) {
        return tagDAO.modifyTag((TagPO) tag.toPO());
    }

    @Override
    public TagVO findTagById(long tagId) {
        return new TagVO(tagDAO.findTagById(tagId));
    }
}