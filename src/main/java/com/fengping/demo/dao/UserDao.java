package com.fengping.demo.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    /**
     * 查询用户名
     * @return
     */
    public String selectUserName();
}
