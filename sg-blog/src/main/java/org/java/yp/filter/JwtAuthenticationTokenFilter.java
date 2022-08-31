package org.java.yp.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.LoginUser;
import org.java.yp.enmu.AppHttpCodeEnum;
import org.java.yp.utils.JwtUtil;
import org.java.yp.utils.RedisCache;
import org.java.yp.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/28 21:16
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws ServletException, IOException {

        //获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //说明改接口不需要登录，直接放行
            filter.doFilter(request, response);
            return;
        }
        //解析获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时 token非法
            //响应告诉前端要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        ///从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId);
        if (Objects.isNull(loginUser)){
            //说明登录过期，提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filter.doFilter(request,response);
    }
}
