package org.java.yp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("org.java.yp.mapper")
@EnableScheduling
public class SangGengBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SangGengBlogApplication.class,args);
    }
}
