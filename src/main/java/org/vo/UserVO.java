package org.vo;


import org.po.PO;
import org.po.UserPO;
import org.util.TransUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class UserVO implements VO {

    private long userId;

    private String name;

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

    public UserVO(long userId, String name, String password,
                  String location, String phone, String mail, String display,
                  String role, double balance, List<TagVO> tags) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.mail = mail;
        this.display = display;
        this.role = role;
        this.balance = balance;
        this.tags = tags;
    }

    public UserVO(UserPO userPO) {
        this.userId = userPO.getUserId();
        this.name = userPO.getName();
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
        return new UserPO(userId, name, password, location, phone, mail, display, role, false, balance, TransUtil.toPOList(tags));
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }
}
