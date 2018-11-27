package com.fengping.demo.service.impl;

import com.fengping.demo.dao.UserDao;
import com.fengping.demo.dataSource.DS;
import com.fengping.demo.redis.RedisUtil;
import com.fengping.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;

    @DS
    @Override
    public String selectUserNameOne() {
        return userDao.selectUserName();
    }

    @DS("datasource2")
    @Override
    /**
     * 测试redis是否可用。将第一次从数据库中查询出的数据放入redis中且设置过期时间为30秒
     * 30秒内再次调用该接口就直接从redis中取数据
     */
    public String selectUserNameTwo() {
        String key = "name";
        Boolean boo = redisUtil.hasKey(key);
        if(boo){
            Object o = redisUtil.get(key);
            System.out.println(o.toString());
            return (String)o;
        }else{
            String name = userDao.selectUserName();
            redisUtil.set(key,name);
            redisUtil.expire(key,30);
            return name;
        }
    }
}
