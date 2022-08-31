package org.java.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-08-29 19:50:18
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

