package com.fcl.fmusic.service;

import com.fcl.fmusic.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    public boolean insert(User user);
    public boolean update(User user);
    public boolean delete(Integer id);
    public boolean deleteByIds(List list);
    public User selectUserById(Integer id);
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize,String userName);
}
