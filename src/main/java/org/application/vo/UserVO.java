package org.application.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.po.PO;
import org.application.po.UserPO;
import org.application.util.TransUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements VO {

    private long userId;

    private String userName;

    private String password;

    private String location;

    private String phone;

    private String mail;

    /**
     * 用户头像图片的url
     */
    private String display;

    private String role;

    private double balance;

    private List<TagVO> tags;

    public UserVO(UserPO userPO) {
        this.userId = userPO.getUserId();
        this.userName = userPO.getName();
        this.password = userPO.getPassword();
        this.location = userPO.getLocation();
        this.phone = userPO.getPhone();
        this.mail = userPO.getMail();
        this.display = userPO.getDisplay();
        this.role = userPO.getRole();
        this.balance = userPO.getBalance();

        this.tags = new ArrayList<>(userPO.getTags().size());
        userPO.getTags().forEach(tagPO -> this.tags.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new UserPO(userId, userName, password, location, phone, mail, display, role, false, balance, TransUtil.toPOList(tags));
    }
}
