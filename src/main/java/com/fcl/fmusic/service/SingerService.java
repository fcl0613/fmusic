package com.fcl.fmusic.service;

import com.fcl.fmusic.entity.Singer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SingerService {
    public boolean insert(Singer singer);
    public boolean update(Singer singer);
    public boolean delete(Integer singerId);
    public boolean deleteByIds(List<Integer> ids);
    public Singer selectById(Integer singerId);
    public List<Singer> selectAllSinger();
    public PageInfo<Singer> selectPage(Integer pageNum, Integer pageSize, String keyword, Byte gender);
}
