package org.java.yp.service;

import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.User;
import org.springframework.stereotype.Service;


public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
