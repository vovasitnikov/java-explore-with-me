package com.github.explore_with_me.main.comment.controller;

import com.github.explore_with_me.main.comment.service.CommentService;
import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.dto.InputCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("admin/comments")
@RequiredArgsConstructor
public class AdminCommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    public void removeCommentByAdmin(@PathVariable Long commentId) {
        commentService.removeCommentById(commentId);
    }

    @PatchMapping("/{commentId}")
    public CommentDto patchComment(@PathVariable Long userId,
                                   @RequestBody InputCommentDto inputCommentDto,
                                   @PathVariable Long commentId) {
        return commentService.changeComment(inputCommentDto, commentId);
    }

}