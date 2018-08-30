package org.application.data.userdata;

import org.application.PlaythingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author miaomuzhi
 * @since 2018/8/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlaythingApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = {org.application.data.userdata.UserDAOImpl.class, org.application.po.UserPO.class})
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void addUser() {
    }
}