package com.hmall.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.cart.domain.po.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface CartMapper extends BaseMapper<Cart> {

    @Update("UPDATE cart SET num = num + 1 WHERE user_id = #{userId} AND item_id = #{itemId}")
    void updateNum(@Param("itemId") Long itemId, @Param("userId") Long userId);

    @Update("UPDATE cart SET num=#{num} WHERE item_id=#{itemId} and business_id=#{businessId} and user_id=#{userId}")
    void updateCart(@Param("itemId") Long itemId, @Param("userId") Long userId,@Param("businessId") Long businessId,@Param("num") Integer num);

    @Delete("DELETE FROM cart WHERE user_id = #{userId} AND business_id = #{businessId} AND item_id=#{itemId} ")
    void removeCart(Long userId, Long businessId, Long itemId);
}
