package org.util;

import org.junit.Test;
import org.po.UserPO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2018/8/11
 */
public class TransUtilTest {

    @Test
    public void removeDeleted() {
        List<UserPO> userPOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserPO userPO = new UserPO();
            userPO.setDeleted(i%2 == 1);
            userPO.setUserId(i);
            userPOList.add(userPO);
        }

        TransUtil.removeDeleted(userPOList);
        assertEquals(5, userPOList.size());
    }
}