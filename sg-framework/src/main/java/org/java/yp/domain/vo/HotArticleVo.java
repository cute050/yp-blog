package org.java.yp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/26 22:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    private Long id;

    //标题
    private String title;

    //访问量
    private Long viewCount;
}
