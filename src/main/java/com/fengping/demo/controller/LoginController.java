package com.fengping.demo.controller;

import com.fengping.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login", method = RequestMethod.POST)
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test() {
        return "xxx";
    }

    @RequestMapping("/selectOne")
    public String selectOne() {
        return userService.selectUserNameOne();
    }

    @RequestMapping("/selectTwo")
    public String selectTwo() {
        return userService.selectUserNameTwo();
    }
}
