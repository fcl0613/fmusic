package com.fcl.fmusic.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultPage {
    private int pageNum;        //当前页码
    private int pageSize;       //每页数量
    private int totalPages;     //页码总数
    private long totalSize;     //记录总数
    private List<?> content;    //数据模型
}
