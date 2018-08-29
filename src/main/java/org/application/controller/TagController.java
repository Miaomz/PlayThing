package org.application.controller;

import org.application.businesslogic.tagbl.TagService;
import org.application.util.ResultMessage;
import org.application.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@RestController
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping("/add_tag")
    public ResultMessage addTag(@RequestParam String tag){
        TagVO tagVO = new TagVO();
        tagVO.setContent(tag);
        return tagService.addTag(tagVO);
    }
}
