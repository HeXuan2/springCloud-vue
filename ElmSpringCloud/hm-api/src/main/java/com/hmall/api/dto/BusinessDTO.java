package com.hmall.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author hexuan
 * @Date 2024/5/16 16:52
 * @PackageName:com.hmall.api.dto
 * @ClassName: BusinessDTO
 * @Description: TODO
 */
@Data
@ApiModel(description = "商家实体")
public class BusinessDTO {

    @ApiModelProperty(value = "商家编号", example = "1")
    private Long businessId;

    @ApiModelProperty(value = "商家名称", example = "某某商店")
    private String businessName;

    @ApiModelProperty(value = "商家地址", example = "某某区某某街道某某号")
    private String businessAddress;

    @ApiModelProperty(value = "商家介绍", example = "提供美味的xxx菜品")
    private String businessExplain;

    @ApiModelProperty(value = "商家图片（base64）", example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...")
    private String businessImg;

    @ApiModelProperty(value = "点餐分类ID", example = "1")
    private Integer ordertypeId;

    @ApiModelProperty(value = "起送费", example = "20.00")
    private BigDecimal startPrice;

    @ApiModelProperty(value = "配送费", example = "5.00")
    private BigDecimal deliveryPrice;

    @ApiModelProperty(value = "备注", example = "无")
    private String remarks;
}
