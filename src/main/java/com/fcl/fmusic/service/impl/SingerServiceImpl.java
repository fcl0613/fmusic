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
    public boolean delete(Integer id) {
        return singerMapper.delete(id) > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        return singerMapper.deleteByIds(ids) > 0;
    }

    @Override
    public Singer selectById(Integer id) {
        return singerMapper.selectById(id);
    }

    @Override
    public List<Singer> selectAllSinger() {
        return singerMapper.selectAllSinger();
    }

    @Override
    public PageInfo<Singer> selectPage(Integer pageNum, Integer pageSize, String keyword, Byte gender) {
        PageHelper.startPage(pageNum,pageSize);
        List<Singer> singerList = singerMapper.selectPage("%"+keyword+"%",gender);
        PageInfo<Singer> pageInfo = new PageInfo<>(singerList);
        return pageInfo;
    }
}
