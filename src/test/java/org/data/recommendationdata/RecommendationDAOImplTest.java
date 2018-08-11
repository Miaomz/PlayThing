package org.data.recommendationdata;

import org.application.PlaythingApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.po.RecommendationPO;
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
 * @since 2018/8/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlaythingApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = {org.data.recommendationdata.RecommendationDAOImpl.class, org.po.RecommendationPO.class})
public class RecommendationDAOImplTest {

    @Autowired
    private RecommendationDAO recommendationDAO;

    private RecommendationPO rpo;
    private RecommendationPO rpo2;

    @Before
    public void setUp(){
        rpo = new RecommendationPO();
        rpo.setRid(0);
        rpo.setContent("test recommendation");

        rpo2 = new RecommendationPO();
        rpo2.setContent("after modified");
    }

    @Test
    @Transactional
    @Rollback
    public void addRecommendation() {
        assertEquals(ResultMessage.SUCCESS, recommendationDAO.addRecommendation(rpo));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteRecommendation() {
        recommendationDAO.addRecommendation(rpo);
        recommendationDAO.deleteRecommendation(rpo.getRid());
        assertEquals(true, recommendationDAO.findRecommendationById(rpo.getRid()).isDeleted());
    }

    @Test
    @Transactional
    @Rollback
    public void modifyRecommendation() {
        recommendationDAO.addRecommendation(rpo);
        rpo2.setRid(rpo.getRid());
        recommendationDAO.modifyRecommendation(rpo2);
        assertEquals("after modified", recommendationDAO.findRecommendationById(rpo.getRid()).getContent());
    }

    @Test
    @Transactional
    @Rollback
    public void findRecommendationById() {
        recommendationDAO.addRecommendation(rpo);
        assertEquals("test recommendation", recommendationDAO.findRecommendationById(rpo.getRid()).getContent());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllRecommendations() {
        recommendationDAO.addRecommendation(rpo);
        recommendationDAO.addRecommendation(rpo2);
        assertEquals(2, recommendationDAO.findAllRecommendations().size());
    }
}