package org.po;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class UserPO implements PO {

    private long userId;

    private String name;

    private String password;

    private String role;

    private boolean isDeleted;

    private double balance;

    private List<TagPO> tags;

    public UserPO(long userId, String name, String password, String role, boolean isDeleted, double balance, List<TagPO> tags) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.isDeleted = isDeleted;
        this.balance = balance;
        this.tags = tags;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TagPO> getTags() {
        return tags;
    }

    public void setTags(List<TagPO> tags) {
        this.tags = tags;
    }
}
