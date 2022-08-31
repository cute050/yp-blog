package org.java.yp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.java.yp.domain.entity.LoginUser;
import org.java.yp.domain.entity.User;
import org.java.yp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/28 16:18
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户 没有抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        // TODO 查询权限信息封装
        //返回用户信息
        return new LoginUser(user);
    }
}
