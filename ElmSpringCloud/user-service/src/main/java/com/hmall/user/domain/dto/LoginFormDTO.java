package com.hmall.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "登录表单实体")
public class LoginFormDTO {

    @ApiModelProperty(value = "用户手机号", required = true)
    @NotNull(message = "用户手机号不能为空")
    private String phone;
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String password;
    @ApiModelProperty(value = "是否记住我", required = false)
    private Boolean rememberMe = false;
}
