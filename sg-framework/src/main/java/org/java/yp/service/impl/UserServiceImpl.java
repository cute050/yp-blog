package org.java.yp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.User;
import org.java.yp.domain.vo.UserInfoVo;
import org.java.yp.mapper.UserMapper;
import org.java.yp.service.UserService;
import org.java.yp.utils.BeanCopyUtils;
import org.java.yp.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-29 21:24:03
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {

        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {

        updateById(user);

        return ResponseResult.okResult();
    }
}

