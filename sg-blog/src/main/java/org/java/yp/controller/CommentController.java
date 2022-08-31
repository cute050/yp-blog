package org.java.yp.controller;

import org.java.yp.constants.SystemConstants;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Comment;
import org.java.yp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/29 20:02
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){

        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){

        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}
