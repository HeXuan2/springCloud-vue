package com.hmall.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author hexuan
 * @Date 2024/5/16 15:17
 * @PackageName:domain.vo
 * @ClassName: BusinessVO
 * @Description: TODO
 */
@Data
@ApiModel(description = "商家VO实体")
public class BusinessVO {
    @ApiModelProperty("商家编号")
    private Long businessId;

    @ApiModelProperty("商家名称")
    private String businessName;

    @ApiModelProperty("商家地址")
    private String businessAddress;

    @ApiModelProperty("商家介绍")
    private String businessExplain;

    @ApiModelProperty("商家图片（base64）")
    private String businessImg;

    @ApiModelProperty("点餐分类")
    private Integer orderTypeId;

    @ApiModelProperty("起送费")
    private BigDecimal startPrice;

    @ApiModelProperty("配送费")
    private BigDecimal deliveryPrice;

    @ApiModelProperty("备注")
    private String remarks;
}

