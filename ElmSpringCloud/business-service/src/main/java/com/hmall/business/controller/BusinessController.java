package com.hmall.business.controller;

import com.hmall.api.dto.BusinessDTO;
import com.hmall.business.service.IBusinessService;
import com.hmall.common.utils.BeanUtils;
import com.hmall.api.vo.BusinessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/5/16 15:06
 * @PackageName:controller
 * @ClassName: businessController
 * @Description: TODO
 */

@Api(tags = "商家管理相关接口")
@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {

    private final IBusinessService businessService;

    @ApiOperation("通过订单Id得到商家列表")
    @GetMapping("/byOrderTypeId")
    public List<BusinessDTO> listBusinessByOrderTypeId(@RequestParam("ordertype_id") Long orderTypeId){
        return businessService.listBusinessByOrderTypeId(orderTypeId);
    }


    @ApiOperation("通过商家Id得到商家列表")
    @GetMapping("/byId")
    public BusinessDTO getBusinessById(@RequestParam("business_id") Long businessId){
        return BeanUtils.copyBean(businessService.getById(businessId), BusinessDTO.class);
    }
}
