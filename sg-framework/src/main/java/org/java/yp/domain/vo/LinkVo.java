package org.java.yp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/28 13:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {

    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;




}
