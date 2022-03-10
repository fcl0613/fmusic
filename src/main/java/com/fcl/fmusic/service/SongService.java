package com.fcl.fmusic.service;

import com.fcl.fmusic.entity.Song;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface SongService {
    public boolean insert(Song song);
    public boolean update(Song song);
    public boolean delete(Integer songId);
    public Song selectById(Integer id);
    public boolean deleteByIds(List list);
    public PageInfo<Song> selectPage(Integer pageNum, Integer pageSize, String songName, Integer singerId);
    public PageInfo<Song> selectAll(Integer pageNum, Integer pageSize, String songName, String singerName);
}
