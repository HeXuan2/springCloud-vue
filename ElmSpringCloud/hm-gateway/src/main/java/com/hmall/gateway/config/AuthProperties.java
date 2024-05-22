package com.hmall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "hm.auth")
//@ConfigurationProperties 是Spring Boot 提供的注解，用于将配置文件中的属性映射到Java类的字段上。
// 在这里，它表示该类将映射以 "hm.auth" 为前缀的配置属性。
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
