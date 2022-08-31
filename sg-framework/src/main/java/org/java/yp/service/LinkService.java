package org.java.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-08-28 12:54:43
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

