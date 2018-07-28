package org.po;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
public class UserPO implements PO {

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

    private boolean isDeleted;

    private double balance;

    private List<TagPO> tags;

    public UserPO(long userId, String name, String password,
                  String location, String phone, String mail, String display,
                  String role, boolean isDeleted, double balance, List<TagPO> tags) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.mail = mail;
        this.display = display;
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
