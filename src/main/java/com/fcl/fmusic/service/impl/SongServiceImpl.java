package com.fcl.fmusic.service.impl;

import com.fcl.fmusic.entity.Song;
import com.fcl.fmusic.mapper.SongMapper;
import com.fcl.fmusic.service.SongService;
import com.fcl.fmusic.vo.SongVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song)>0;
    }

    @Override
    public boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    @Override
    public boolean delete(Integer songId) {
        return songMapper.deleteById(songId)>0;
    }

    @Override
    public Song selectById(Integer id) {
        return songMapper.selectById(id);
    }

    @Override
    public boolean deleteByIds(List list) {
        return songMapper.deleteByIds(list) > 0;
    }

    @Override
    public PageInfo<Song> selectPage(Integer pageNum, Integer pageSize, String songName, Integer singerId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Song> songs = songMapper.selectPage("%"+songName+"%", singerId);
        PageInfo<Song> pageInfo = new PageInfo<>(songs);
        return pageInfo;
    }

    @Override
    public PageInfo<SongVo> selectAll(Integer pageNum, Integer pageSize, String songName, String singerName) {
        PageHelper.startPage(pageNum, pageSize);
        List<SongVo> songs = songMapper.selectAll("%"+songName+"%", "%"+singerName+"%");
        PageInfo<SongVo> pageInfo = new PageInfo<>(songs);
        return pageInfo;
    }
}