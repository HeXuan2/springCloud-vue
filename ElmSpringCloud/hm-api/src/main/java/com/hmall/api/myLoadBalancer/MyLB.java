package com.hmall.api.myLoadBalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/5/18 15:37
 * @PackageName:com.hmall.api.myLoadBalancer
 * @ClassName: MyLB
 * @Description: TODO
 */
public class MyLB implements ReactorServiceInstanceLoadBalancer {

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public MyLB(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable();
        if (supplier == null) {
            // 处理无法获取服务实例列表提供者的情况，可能需要返回一个默认的响应或者抛出异常
            // 这里暂时返回一个空的响应
            return Mono.empty();
        }
        return supplier.get(request).next()
                .map(serviceInstances -> processInstanceResponse(supplier, serviceInstances));
    }
    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier,
                                                              List<ServiceInstance> serviceInstances) {
        Response<ServiceInstance> serviceInstanceResponse = getInstanceResponse(serviceInstances);
        return serviceInstanceResponse;
    }

    // ServiceInstance作为注册表中的实例
    // 此方法写入自己的负载均衡策略逻辑即可。
    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> serviceInstances) {
        if(serviceInstances.isEmpty()){
            return new EmptyResponse();
        }

        for (ServiceInstance serviceInstance : serviceInstances) {
            // 只返回8001。
            if (serviceInstance.getPort() == 8001) {
                return new DefaultResponse(serviceInstance);
            }
        }
        return new EmptyResponse();
    }
}
