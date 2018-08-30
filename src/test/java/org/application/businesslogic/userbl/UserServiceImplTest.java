package org.application.businesslogic.userbl;

import org.application.PlaythingApplication;
import org.application.vo.UserVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.application.util.ConstantString.ROLE_USER;
import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2018/8/30
 */
@SpringBootTest(classes = PlaythingApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = {org.application.po.UserPO.class})
public class UserServiceImplTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void addUser() {
        userService.addUser(new UserVO());
        userService.addUser(new UserVO());
        assertEquals(ROLE_USER, userService.findUserByName("try").getRole());
    }
}