package com.hmall.api.myLoadBalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @Author hexuan
 * @Date 2024/5/18 15:38
 * @PackageName:com.hmall.api.myLoadBalancer
 * @ClassName: MyConfig
 * @Description: TODO
 */
@LoadBalancerClient(value = "item-service", configuration = MyConfig.class)
public class MyConfig {

    // 配置负载均衡策略
    @Bean
    public ReactorLoadBalancer<ServiceInstance> myLB(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new MyLB(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class));
    }
}
