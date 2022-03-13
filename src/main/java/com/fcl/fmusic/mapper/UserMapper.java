package com.fcl.fmusic.mapper;

import com.fcl.fmusic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public int insert(User user);
    public int update(User user);
    public int deleteByIds(List list);
    public int deleteById(Integer id);
    public User selectUserById(Integer id);
    public List<User> selectPage(@Param("userName") String username);
}