package org.java.yp.constants;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/27 8:15
 */

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    public static final String STATUS_NORMAL="0";
    /*/**
     *友链状态为审核通过
     * @Description
     * @Date 13:16 2022/8/28
     * @Param @param null
     * @return
     **/
    public static final String LINK_STATUS_NORMAL="0";

    /**
     *
     * @Description
     * @Date 17:22 2022/8/31
     * @Param @param null
     * @return 评论类型 ：0 为 文章评论
     **/
    public static final String ARTICLE_COMMENT = "0";

    /**
     *
     * @Description
     * @Date 17:24 2022/8/31
     * @Param @param null
     * @return 评论类型 ：1 为 友链评论
     **/
    public static final String LINK_COMMENT = "1";
}
