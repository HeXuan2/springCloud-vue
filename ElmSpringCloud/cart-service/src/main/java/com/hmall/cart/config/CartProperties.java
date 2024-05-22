package com.hmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author hexuan
 * @Date 2024/2/27 22:16
 * @PackageName:com.hmall.cart.config
 * @ClassName: CartProperties
 * @Description: TODO
 */

@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxItems;
}
