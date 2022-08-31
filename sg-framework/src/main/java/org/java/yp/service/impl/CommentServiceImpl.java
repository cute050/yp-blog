package org.java.yp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.java.yp.constants.SystemConstants;
import org.java.yp.domain.ResponseResult;
import org.java.yp.domain.entity.Comment;
import org.java.yp.domain.vo.CommentVo;
import org.java.yp.domain.vo.PageVo;
import org.java.yp.enmu.AppHttpCodeEnum;
import org.java.yp.exception.SystemException;
import org.java.yp.mapper.CommentMapper;
import org.java.yp.service.CommentService;
import org.java.yp.service.UserService;
import org.java.yp.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-08-29 19:50:20
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commType, Long articleId, Integer pageNum, Integer pageSize) {

        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commType),Comment::getArticleId,articleId);
        //根评论rootId 为-1
        queryWrapper.eq(Comment::getRootId,-1);

        //评论类型
        queryWrapper.eq(Comment::getType,commType);

        //分页查询
        Page<Comment> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList= toCommentVoList(page.getRecords());
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {

        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     *
     * @Description
     * @Date 21:16 2022/8/30
     * @Param @param null
     * @return id 根评论的id
     **/
    private List<CommentVo> getChildren(Long id) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments=list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    public List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {
            //通过creatyBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentUserId() != -1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;


    }
}

