package com.fcl.fmusic.mapper;

import com.fcl.fmusic.entity.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {
    public int insert(Song song);
    public int update(Song song);
    public int deleteById(Integer id);
    public int deleteByIds(List list);
    public Song selectById(Integer id);
    public List<Song> selectPage(@Param("songName") String songName, @Param("singerId") Integer singerId);
    public List<Song> selectAll(@Param("songName") String songName, @Param("singerName") String singerName);
}
