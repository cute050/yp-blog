package org.java.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
