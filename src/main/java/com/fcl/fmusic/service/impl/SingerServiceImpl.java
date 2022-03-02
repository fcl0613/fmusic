package com.fcl.fmusic.service.impl;

import com.fcl.fmusic.entity.Singer;
import com.fcl.fmusic.mapper.SingerMapper;
import com.fcl.fmusic.service.SingerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean insert(Singer singer) {
        return singerMapper.insert(singer) > 0;
    }

    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return singerMapper.delete(id) > 0;
    }

    @Override
    public Singer selectById(Long id) {
        return singerMapper.selectById(id);
    }

    @Override
    public List<Singer> selectAllSinger() {
        return singerMapper.selectAllSinger();
    }

    @Override
    public List<Singer> selectSingerByName(String singerName) {
        return singerMapper.selectSingerByName(singerName);
    }

    @Override
    public List<Singer> selectSingerByGender(Integer gender) {
        return singerMapper.selectSingerByGender(gender);
    }

    @Override
    public PageInfo<Singer> selectPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Singer> singerList = singerMapper.selectPage();
        PageInfo<Singer> pageInfo = new PageInfo<>(singerList);
        return pageInfo;
    }
}
