package com.fcl.fmusic.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SongVo {
    private Integer songId;
    private String songName;
    private String singerName;
    private String information;
    private Date creatTime;
    private Date updateTime;
    private String pic;     //歌曲封面路径
    private String lyrics;  //歌词
    private String url;
}
