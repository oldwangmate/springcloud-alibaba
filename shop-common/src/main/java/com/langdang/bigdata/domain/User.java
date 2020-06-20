package com.langdang.bigdata.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//用户
@Entity(name = "shop_user")//实体类跟数据表的对应

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据库自增
    private Integer uid;//主键
    private String username;//用户名
    private String password;//密码
    private String telephone;//手机号

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}