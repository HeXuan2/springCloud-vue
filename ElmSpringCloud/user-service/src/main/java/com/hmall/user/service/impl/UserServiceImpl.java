package com.hmall.user.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.common.exception.BadRequestException;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.exception.ForbiddenException;
import com.hmall.common.utils.UserContext;
import com.hmall.user.config.JwtProperties;
import com.hmall.user.domain.dto.LoginFormDTO;
import com.hmall.user.domain.dto.RegisterFormDTO;
import com.hmall.user.domain.po.User;
import com.hmall.user.domain.vo.UserLoginVO;
import com.hmall.user.enums.UserStatus;
import com.hmall.user.mapper.UserMapper;
import com.hmall.user.service.IUserService;
import com.hmall.user.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVO login(LoginFormDTO loginDTO) {
        // 1.数据校验
        String userPhone= loginDTO.getPhone();
        String password = loginDTO.getPassword();

        // 2.根据用户名或手机号查询

        User user = lambdaQuery().eq(User::getPhone, userPhone).one();

        Assert.notNull(user, "用户不存在的错误");

        // 3.校验是否禁用
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException("用户被冻结");
        }
        // 4.校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        // 5.生成TOKEN
        String token = jwtTool.createToken(user.getId(), jwtProperties.getTokenTTL());
        // 6.封装VO返回
        UserLoginVO vo = new UserLoginVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setBalance(user.getBalance());
        vo.setToken(token);
        return vo;
    }


    @Override
    public RegisterFormDTO register(RegisterFormDTO registerFormDTO) {
// 1.数据校验
        String userPhone = registerFormDTO.getPhone();
        String password = registerFormDTO.getPassword();
        // 2.根据用户名或手机号查询
        User userOne = lambdaQuery().eq(User::getPhone,userPhone).one();
        Assert.isNull(userOne, "手机账号已存在");
        registerFormDTO.setPassword(passwordEncoder.encode(password));

        User user=new User();
        user.setPassword(registerFormDTO.getPassword());
        user.setSex(registerFormDTO.getSex());
        user.setUsername(registerFormDTO.getUsername());
        user.setPhone(registerFormDTO.getPhone());
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();
        user.setCreateTime(createTime);
        user.setUpdateTime(updateTime);
        save(user);

        return registerFormDTO;
    }

    @Override
    public void deductMoney(String pw, Integer totalFee) {
        log.info("开始扣款");
        // 1.校验密码
        User user = getById(UserContext.getUser());
        if(user == null || !passwordEncoder.matches(pw, user.getPassword())){
            // 密码错误
            throw new BizIllegalException("用户密码错误");
        }

        // 2.尝试扣款
        try {
            baseMapper.updateMoney(UserContext.getUser(), totalFee);
        } catch (Exception e) {
            throw new RuntimeException("扣款失败，可能是余额不足！", e);
        }
        log.info("扣款成功");
    }


}
