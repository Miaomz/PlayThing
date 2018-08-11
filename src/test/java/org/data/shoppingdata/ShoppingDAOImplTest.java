package org.data.shoppingdata;

import org.application.PlaythingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2018/8/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlaythingApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = {org.data.shoppingdata.ShoppingDAOImpl.class, org.po.CommodityPO.class})
public class ShoppingDAOImplTest {

    @Autowired
    private ShoppingDAO shoppingDAO;

    @Test
    public void addCommodity() {
    }

    @Test
    public void addCommodityByInquiry() {
    }

    @Test
    public void deleteCommodity() {
    }

    @Test
    public void modifyCommodity() {
    }

    @Test
    @Transactional
    @Rollback
    public void findCommodityById() {
        assertEquals(null, shoppingDAO.findCommodityById(0));
    }

    @Test
    public void findCommodityByInquiry() {
    }

    @Test
    public void findAllCommodities() {
    }

    @Test
    public void buyCommodity() {
    }

    @Test
    public void addInquiry() {
    }

    @Test
    public void deleteInquiry() {
    }

    @Test
    public void modifyInquiry() {
    }

    @Test
    public void findInquiryById() {
    }

    @Test
    public void findAllInquiries() {
    }

    @Test
    public void findAllPurchases() {
    }
}