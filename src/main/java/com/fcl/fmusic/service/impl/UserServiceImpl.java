package com.fcl.fmusic.service.impl;

import com.fcl.fmusic.entity.User;
import com.fcl.fmusic.mapper.UserMapper;
import com.fcl.fmusic.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.deleteById(id)>0;
    }

    @Override
    public boolean deleteByIds(List list) {
        return userMapper.deleteByIds(list)>0;
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, String userName) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.selectPage("%"+userName+"%");
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}
