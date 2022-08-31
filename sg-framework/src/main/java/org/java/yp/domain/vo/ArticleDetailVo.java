package org.java.yp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/28 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {

    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    //所属分类名字
    private String categoryName;
    //文章内容
    private String content;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;

    private Date createTime;
}
