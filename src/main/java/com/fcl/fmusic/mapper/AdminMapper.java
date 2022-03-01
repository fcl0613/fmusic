package com.fcl.fmusic.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    public int adminLogin(String username, String password);
}
