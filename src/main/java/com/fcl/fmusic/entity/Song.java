package com.fcl.fmusic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Song {
    private Integer songId;
    private String songName;
    private Integer singerId;
    private String information;
    private Date creatTime;
    private Date updateTime;
    private String pic;     //歌曲封面路径
    private String lyrics;  //歌词
    private String url;
}
