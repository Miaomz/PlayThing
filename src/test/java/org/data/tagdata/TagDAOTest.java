package org.data.tagdata;

import org.application.PlaythingApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.po.TagPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.util.ResultMessage;

import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2018/7/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlaythingApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = {org.data.tagdata.TagDAOImpl.class, org.po.TagPO.class})
public class TagDAOTest {

    @Autowired
    private TagDAO tagDAO;

    private TagPO tagPO;
    private TagPO tagPO1;

    @Before
    public void setup(){
        tagPO = new TagPO(0, false, "Test for spring data");
        tagPO1 = new TagPO(0, false, "After modified");
    }

    @Test
    @Transactional
    @Rollback
    public void addTag() {
        assertEquals(ResultMessage.SUCCESS, tagDAO.addTag(tagPO));
    }

    @Test
    @Transactional
    @Rollback
    public void modifyTag() {
        tagDAO.addTag(tagPO);
        assertEquals(ResultMessage.SUCCESS, tagDAO.modifyTag(tagPO1));
    }

    @Test
    @Transactional
    @Rollback
    public void findTagById() {
        tagDAO.addTag(tagPO);
        assertEquals("Test for spring data", tagDAO.findTagById(tagPO.getTagId()).getContent());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTags() {
        tagDAO.addTag(tagPO);
        tagDAO.addTag(tagPO1);
        assertEquals(2, tagDAO.findAllTags().size());
    }
}