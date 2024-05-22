package com.hmall.common.config;

import com.hmall.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author hexuan
 * @Date 2024/1/22 21:12
 * @PackageName:com.hmall.common.config
 * @ClassName: MvcConfig
 * @Description: TODO
 */

//#不过，需要注意的是，这个配置类默认是不会生效的，因为它所在的包是com.hmall.common.config，与其它微服务的扫描包不一致，无法被扫描到，因此无法生效。
//#基于SpringBoot的自动装配原理，我们要将其添加到resources目录下的META-INF/spring.factories文件中：
//这里的错误找了很久
@Configuration
@ConditionalOnClass(DispatcherServlet.class)
//配置拦截器
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
