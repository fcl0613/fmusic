package com.fcl.fmusic.utils;

import com.fcl.fmusic.entity.ResultPage;
import com.github.pagehelper.PageInfo;

/**
 * 封装分页对象
 */
public class PageUtils {
    public static ResultPage getResultPage(PageInfo<?> pageInfo){
        ResultPage resultPage = new ResultPage();
        resultPage.setPageNum(pageInfo.getPageNum());
        resultPage.setPageSize(pageInfo.getPageSize());
        resultPage.setTotalPages(pageInfo.getPages());
        resultPage.setTotalSize(pageInfo.getTotal());
        resultPage.setContent(pageInfo.getList());
        return resultPage;
    }
}
