package com.fcl.fmusic.service;

import com.fcl.fmusic.entity.Singer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SingerService {
    public boolean insert(Singer singer);
    public boolean update(Singer singer);
    public boolean delete(Long id);
    public Singer selectById(Long id);
    public List<Singer> selectAllSinger();
    public List<Singer> selectSingerByName(String singerName);
    public List<Singer> selectSingerByGender(Integer gender);
    public PageInfo<Singer> selectPage(Integer pageNum, Integer pageSize);
}
