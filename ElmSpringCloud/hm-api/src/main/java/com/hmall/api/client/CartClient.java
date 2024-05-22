package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/1/21 15:57
 * @PackageName:com.hmall.api.client
 * @ClassName: CartClient
 * @Description: TODO
 */
@FeignClient("cart-service")
public interface CartClient {

    @DeleteMapping("/carts/byItemIds")
    void deleteCartItemByIds(@RequestParam("ids") Collection<Long> ids);

}
