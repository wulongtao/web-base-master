package com.xxh.web.controller.bean;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 小小黑
 */
public class User implements Serializable {
    @NotBlank
    private String loginname;

    private Date birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @NumberFormat(style = Style.NUMBER, pattern = "#,###")
    private Integer total;

    @NumberFormat(style = Style.PERCENT)
    private String discout;

    @NumberFormat(style = Style.CURRENCY)
    private double money;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDiscout() {
        return discout;
    }

    public void setDiscout(String discout) {
        this.discout = discout;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginname='" + loginname + '\'' +
                ", birthday=" + birthday +
                ", createTime=" + createTime +
                ", total=" + total +
                ", discout='" + discout + '\'' +
                ", money=" + money +
                '}';
    }
}
