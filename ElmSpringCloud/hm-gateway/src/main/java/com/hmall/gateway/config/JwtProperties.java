package com.hmall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "hm.jwt")
//@ConfigurationProperties 是Spring Boot 提供的注解，用于将配置文件中的属性映射到Java类的字段上。
// 在这里，它表示该类将映射以 "hm.jwt" 为前缀的配置属性。
//加载配置文件中的hm:jwt:部分，真正读取密钥在SecurityConfig类文件中
public class JwtProperties {
    private Resource location;
    private String password;
    private String alias;
    private Duration tokenTTL = Duration.ofMinutes(10);
}
