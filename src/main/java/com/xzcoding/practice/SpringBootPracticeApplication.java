package com.xzcoding.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sunxiaozhi
 * @date 2022-04-10 21:16
 */
@SpringBootApplication
@MapperScan("com.xzcoding.practice.Mapper")
public class SpringBootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPracticeApplication.class, args);
    }

}
