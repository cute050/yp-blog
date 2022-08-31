package org.java.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-08-27 10:18:25
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

