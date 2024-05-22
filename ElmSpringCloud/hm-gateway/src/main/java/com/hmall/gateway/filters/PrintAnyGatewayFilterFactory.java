package com.hmall.gateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/1/22 19:02
 * @PackageName:com.hmall.gateway.filters
 * @ClassName: PrintAnyGatewayFilterFactory
 * @Description: TODO
 */
@Component
public class PrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<PrintAnyGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String a=config.getA();
                String b=config.getB();
                String c=config.getC();
                System.out.println("a="+a);
                System.out.println("b="+b);
                System.out.println("c="+c);
                System.out.println("print any filter running");
                return chain.filter(exchange);
            }
        },1);
    }



    // 自定义配置属性，成员变量名称很重要，下面会用到
    @Data
    public static class Config{
        private String a;
        private String b;
        private String c;//和配置文件的参数一一对应
    }

    //将config字节码传递给父类，父类负责帮我们读取yaml配置
    public PrintAnyGatewayFilterFactory() {
        super(Config.class);
    }
    // 将变量名称依次返回，顺序很重要，将来读取参数时需要按顺序获取
    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a","b","c");
    }
}
