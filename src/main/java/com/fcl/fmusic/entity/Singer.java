package com.fcl.fmusic.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 歌手
 */
@Data
@ToString
public class Singer implements Serializable {
    private Integer singerId;
    private String singerName;
    private Byte gender;        //0:男1:女2:组合3:保密
    private String pic;     //头像
    @JSONField(format="yyyy-MM-dd")
    private String birth;     //生日
    private String location;        //地区
    private String information;     //简介
}
