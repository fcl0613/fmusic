package com.fcl.fmusic.service.impl;

import com.fcl.fmusic.entity.Singer;
import com.fcl.fmusic.service.SingerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SingerServiceImplTest {

    @Autowired
    SingerService singerService;
    @Test
    void selectPage(){
        System.out.println(singerService.selectPage(1,2));
    }
}