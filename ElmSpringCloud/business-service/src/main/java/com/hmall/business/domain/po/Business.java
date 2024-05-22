package com.hmall.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author hexuan
 * @since 2023-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家编号
     */
    @TableId(value = "business_id", type = IdType.AUTO)
    private Long businessId;

    /**
     * 商家名称
     */
    private String businessName;

    /**
     * 商家地址
     */
    private String businessAddress;

    /**
     * 商家介绍
     */
    private String businessExplain;

    /**
     * 商家图片（base64）
     */
    private String businessImg;

    /**
     * 点餐分类
     */
    private Integer ordertypeId;

    /**
     * 起送费
     */
    private BigDecimal startPrice;

    /**
     * 配送费
     */
    private BigDecimal deliveryPrice;

    /**
     * 备注
     */
    private String remarks;
}
