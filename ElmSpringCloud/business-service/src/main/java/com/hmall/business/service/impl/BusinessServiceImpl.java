package com.hmall.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.dto.BusinessDTO;
import com.hmall.business.mapper.BusinessMapper;
import com.hmall.business.service.IBusinessService;
import com.hmall.common.utils.BeanUtils;
import com.hmall.business.domain.po.Business;
import com.hmall.api.vo.BusinessVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hexuan
 * @Date 2024/5/16 15:28
 * @PackageName:service.impl
 * @ClassName: BusinessServiceImpl
 * @Description: TODO
 */
@Service
@RequiredArgsConstructor
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

    public List<BusinessDTO> listBusinessByOrderTypeId(Long orderTypeId) {
        QueryWrapper<Business> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ordertype_id",orderTypeId);
        return BeanUtils.copyList(list(queryWrapper), BusinessDTO.class);

    }
}
