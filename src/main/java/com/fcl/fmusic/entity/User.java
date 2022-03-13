package com.fcl.fmusic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User{
    private Integer userId;
    private String userName;
    private String password;
    private String salt;            //用户密码加密需要的盐
    private Byte gender;
    private String phoneNum;
    private String email;
    private String birth;
    private String signature;       //签名
    private String location;
    private String avatar;          //头像
    private String activationTime; //验证码失效的时间
    private Byte isValid;          //账号是否有效
    private String confirmCode;    //邮件确认码
    private Date creatTime;
    private Date updateTime;
}
