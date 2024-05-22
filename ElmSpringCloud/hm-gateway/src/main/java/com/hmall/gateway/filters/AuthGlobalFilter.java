package com.hmall.gateway.filters;

import cn.hutool.core.text.AntPathMatcher;
import com.hmall.common.exception.UnauthorizedException;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/1/22 19:22
 * @PackageName:com.hmall.gateway.filters
 * @ClassName: AuthGlobalFilter
 * @Description: TODO
 */

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    private final AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        // 判断是否是内部调用，如果是，则拦截并返回 FORBIDDEN 状态
        if (antPathMatcher.match("**/inner/**", path)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap("无权限".getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(dataBuffer));
        }

        //2. 判断是否需要做登录拦截
        if(isExclude(request.getPath().toString())){//判断是否需要被放行
            //放行
            return chain.filter(exchange);
        }
        //3. 获取token
        String token=null;

        List<String> headers = request.getHeaders().get("authorization");

        if(headers!=null && !headers.isEmpty()){
            token=headers.get(0);
        }
        Long userId=null;
        //4. 校验并解析token
        try {
             userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException e){
            //拦截，设置响应状态码
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //5. 传递用户信息
        //这一行使用builder对象为新的请求设置一个自定义的HTTP头。
        // 具体来说，它在HTTP请求的头部中添加了一个名为 "user-info" 的字段，
        // 字段的值是前面转换的userId的字符串表示。
        String userInfo=userId.toString();
        System.out.println("创建userInfo的地方==="+userInfo);
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        //6. 放行
        return chain.filter(swe);
    }

    private boolean isExclude(String path) {
        for(String pathPattern:authProperties.getExcludePaths()){
            if (antPathMatcher.match(pathPattern,path)){
                return true;
            }
        }
        return false;

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
