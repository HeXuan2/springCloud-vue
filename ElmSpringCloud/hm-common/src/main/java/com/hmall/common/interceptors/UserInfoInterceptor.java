package com.hmall.common.interceptors;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author hexuan
 * @Date 2024/1/22 21:05
 * @PackageName:com.hmall.common.interceptors
 * @ClassName: UserInfoInterceptor
 * @Description: TODO
 */
@RequiredArgsConstructor
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取登录用户信息
        String userInfo=request.getHeader("user-info");//这里面存的是用户ID

        System.out.println("user-info拿到"+userInfo);
        //2. 判断是否获取了用户，如果有，存入Threadlocal
        if(StrUtil.isNotBlank(userInfo)){
            UserContext.setUser(Long.valueOf(userInfo));
            System.out.println("user-info存入UserContext"+userInfo);
        }
        //3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清理用户
        UserContext.removeUser();
    }
}
