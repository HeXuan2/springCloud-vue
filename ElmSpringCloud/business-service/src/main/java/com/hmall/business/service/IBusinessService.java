package com.hmall.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.api.dto.BusinessDTO;
import com.hmall.business.domain.po.Business;
import com.hmall.api.vo.BusinessVO;

import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/5/16 15:07
 * @PackageName:service
 * @ClassName: businessService
 * @Description: TODO
 */
public interface IBusinessService extends IService<Business> {
    List<BusinessDTO> listBusinessByOrderTypeId(Long orderTypeId);
}
