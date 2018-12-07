package com.fengping.demoeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoEurekaclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaclientApplication.class, args);
    }
}
