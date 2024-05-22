package com.hmall.business;

import com.hmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author hexuan
 * @Date 2024/5/16 15:03
 * @PackageName:PACKAGE_NAME
 * @ClassName: com.hmll.businessApplication
 * @Description: TODO
 */
@EnableFeignClients(basePackages = "com.hmall.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.hmall.business.mapper")
@SpringBootApplication
public class businessApplication {
    public static void main(String[] args) {
        SpringApplication.run(businessApplication.class, args);
    }
}
