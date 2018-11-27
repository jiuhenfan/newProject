package com.fengping.demo.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FastDfsDao {
    /**
     * 向数据库中插入文件路径数据
     * @param filePath
     */
    void insertFileData(String filePath);

    /**
     * 向数据库中删除文件相关数据
     * @param filePath
     */
    void deleteFileData(String filePath);
}
