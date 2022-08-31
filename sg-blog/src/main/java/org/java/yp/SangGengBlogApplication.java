package org.java.yp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.java.yp.mapper")
public class SangGengBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SangGengBlogApplication.class,args);
    }
}
