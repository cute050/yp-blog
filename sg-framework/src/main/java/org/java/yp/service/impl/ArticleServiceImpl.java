package org.java.yp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.java.yp.constants.SystemConstants;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Article;
import org.java.yp.domain.entity.Category;
import org.java.yp.domain.vo.ArticleDetailVo;
import org.java.yp.domain.vo.ArticleListVo;
import org.java.yp.domain.vo.HotArticleVo;
import org.java.yp.domain.vo.PageVo;
import org.java.yp.mapper.ArticleMapper;
import org.java.yp.service.ArticleService;
import org.java.yp.service.CategoryService;
import org.java.yp.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page=new Page(1,10);
        page(page,queryWrapper);
//
//        Page page = lambdaQuery().eq(Article::getStatus, 0)
//                .orderByDesc(Article::getViewCount)
//                .page(new Page(1, 10));
        List<Article> articles = page.getRecords();

        /**
         * 正式文章
         * 按照浏览量排序
         * 最多只查询10条
         */
//        List<HotArticleVo> articleVos=new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            //Bean拷贝
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }


        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行操作
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        List<Article> articles = page.getRecords();
        //查询categoryName
        articles = articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

//        //articleId去查询articleName进行设置
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//
//        }
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {

        //根据id查询文章
        Article article = getById(id);
        //转换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}
