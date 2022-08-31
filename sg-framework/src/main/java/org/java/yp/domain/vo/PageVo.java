package org.java.yp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/27 18:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    private List rows;
    private Long total;
}
