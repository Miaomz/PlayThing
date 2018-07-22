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

    private String role;

    private double balance;

    private List<TagVO> tags;

    public UserVO(long userId, String name, String password, String role, double balance, List<TagVO> tags) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.tags = tags;
    }

    public UserVO(UserPO userPO) {
        this.userId = userPO.getUserId();
        this.name = userPO.getName();
        this.password = userPO.getPassword();
        this.role = userPO.getRole();
        this.balance = userPO.getBalance();

        this.tags = new ArrayList<>(userPO.getTags().size());
        userPO.getTags().forEach(tagPO -> this.tags.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new UserPO(userId, name, password, role, false, balance, TransUtil.toPOList(tags));
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
