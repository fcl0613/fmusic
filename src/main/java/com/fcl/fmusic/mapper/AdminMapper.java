package com.fcl.fmusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {
    public int verifyPassword(String account, String password);
}
