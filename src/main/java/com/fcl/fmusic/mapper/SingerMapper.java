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
    public int delete(Long id);
    public Singer selectById(Long id);
    public List<Singer> selectAllSinger();
    public List<Singer> selectSingerByName(String singerName);
    public List<Singer> selectSingerByGender(Integer gender);
    public List<Singer> selectPage();
}
