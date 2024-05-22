package com.hmall.cart.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hmall.cart.domain.dto.CartFormDTO;
import com.hmall.cart.domain.po.Cart;
import com.hmall.cart.domain.vo.CartVO;
import com.hmall.cart.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Api(tags = "购物车相关接口")
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @ApiOperation("添加商品到购物车")
    @PostMapping
    public void addItem2Cart(@Valid @RequestBody CartFormDTO cartFormDTO){
        cartService.addItem2Cart(cartFormDTO);
    }

    @ApiOperation("更新购物车数据")
    @PutMapping
    public void updateCart(@RequestBody Cart cart){
        cartService.updateCart(cart);
    }

    @ApiOperation("删除购物车中商品")

    @DeleteMapping
    public void deleteCartItem(@RequestBody Cart cart){
        cartService.removeCart(cart);
    }

    @ApiOperation("查询购物车列表")
    @SentinelResource("hot")
    @GetMapping
    public List<CartVO> queryMyCarts(@RequestParam("business_id") Long business_id){
        return cartService.queryMyCarts(business_id);
    }

    @ApiOperation("批量删除购物车中商品")
    @ApiImplicitParam(name = "ids", value = "购物车条目id集合")
    @DeleteMapping("/byItemIds")
    public void deleteCartItemByIds(@RequestParam("ids") Collection<Long> ids){
        cartService.removeByItemIds(ids);
    }
}
