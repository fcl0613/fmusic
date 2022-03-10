package com.fcl.fmusic.mapper;

import com.fcl.fmusic.entity.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SingerMapper {
    public int insert(Singer singer);
    public int update(Singer singer);
    public int delete(Integer singerId);
    public int deleteByIds(List<Integer> ids);
    public Singer selectById(Integer singerId);
    public List<Singer> selectAllSinger();
    public List<Singer> selectPage(String singerName,Byte gender);
}
