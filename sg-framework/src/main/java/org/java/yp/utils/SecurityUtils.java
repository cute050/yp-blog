package org.java.yp.utils;

import org.java.yp.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/30 21:56
 */

public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
