package com.fcl.fmusic.service.impl;

import com.fcl.fmusic.mapper.AdminMapper;
import com.fcl.fmusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public boolean verifyPassword(String account, String password) {
        return adminMapper.verifyPassword(account,password)>0;
    }
}
