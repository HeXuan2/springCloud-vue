package com.hmall.api.config;


import com.hmall.api.client.fallback.ItemClientFallback;
import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @Author hexuan
 * @Date 2024/1/21 15:13
 * @PackageName:com.hmall.api.config
 * @ClassName: DefaultFeignConfig
 * @Description: TODO
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor(){
//        return new UserInfoInterceptor();
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                Long userId=UserContext.getUser();
                if (userId!=null){
                    template.header("user-info", userId.toString());
                }

            }
        };
    }
    @Bean
    public ItemClientFallback itemClientFallbackFactory(){
        return new ItemClientFallback();
    }
}
