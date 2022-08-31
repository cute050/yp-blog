package org.java.yp.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/27 18:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {


    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名字
    private String categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;

    private Date createTime;
}
