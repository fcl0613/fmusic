package com.fcl.fmusic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员
 */
@Data
public class Admin implements Serializable {
    private Long id;
    private String name;
    private String password;
}
