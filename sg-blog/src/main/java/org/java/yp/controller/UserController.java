package org.java.yp.controller;

import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.User;
import org.java.yp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/31 18:08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
}
