package com.github.explore_with_me.main.comment.service;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.dto.InputCommentDto;

import java.util.List;


public interface CommentService {
    CommentDto createComment(InputCommentDto inputCommentDto,
                             Long authorId,
                             Long eventId);

    List<CommentDto> getEventComments(Long eventId);

    CommentDto changeComment(InputCommentDto inputCommentDto,
                             Long authorId,
                             Long eventId,
                             Long commentId);

    void removeByCommentIdAndAuthorId(Long commentId, Long authorId);

    void removeCommentById(Long commentId);

    CommentDto changeComment(InputCommentDto inputCommentDto, Long commentId);

    CommentDto getCommentById(Long commentId);
}
