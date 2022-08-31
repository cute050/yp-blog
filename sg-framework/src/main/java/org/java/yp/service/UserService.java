package org.java.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-08-29 21:24:02
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);
}

