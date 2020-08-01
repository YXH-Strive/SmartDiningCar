package com.yxh;

import com.yxh.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//注册listener
@ServletComponentScan
@Import(SpringUtil.class)
public class SmartdiningcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartdiningcarApplication.class, args);
    }

}
