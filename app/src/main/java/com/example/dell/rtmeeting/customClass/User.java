package com.example.dell.rtmeeting.customClass;

/**
 * 使用者
 *  数据成员有
 *      姓名
 *      性别
 *      头像
 *      邮箱
 *      职位
 * Created by dell on 2018/1/16.
 */

public class User {
    private String name;
//    private char sex;
    private  int imageId;
    private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User(String name, String mail, int imageId) {

        this.name = name;
        this.imageId = imageId;
        this.mail = mail;
    }
    //    private char position;
}
