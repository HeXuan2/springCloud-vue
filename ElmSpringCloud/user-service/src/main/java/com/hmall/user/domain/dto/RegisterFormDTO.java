package com.hmall.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author hexuan
 * @Date 2024/5/21 16:25
 * @PackageName:com.hmall.user.domain.dto
 * @ClassName: RegisterFormDTO
 * @Description: TODO
 */

@Data
@ApiModel(description = "注册表单实体")

public class RegisterFormDTO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String password;

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别", required = true)
    private Integer sex;

}
