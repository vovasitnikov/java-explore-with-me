package com.github.explore_with_me.main.comment.service;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.dto.InputCommentDto;
import com.github.explore_with_me.main.comment.dto.UpdateCommentDto;

import java.util.List;


public interface CommentService {
    CommentDto createComment(InputCommentDto inputCommentDto,
                             Long authorId,
                             Long eventId);

    List<CommentDto> getEventComments(Long eventId);

    CommentDto changeComment(UpdateCommentDto updateCommentDto,
                             Long authorIdd);

    void removeByCommentIdAndAuthorId(Long commentId, Long authorId);

    void removeCommentById(Long commentId);

    CommentDto changeComment(UpdateCommentDto updateCommentDto);

    CommentDto getCommentById(Long commentId);

    List<CommentDto> getUserCommentsByEventId(Long userId, Long eventId);
}
