package com.github.explore_with_me.main.comment.controller;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.dto.InputCommentDto;
import com.github.explore_with_me.main.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController()
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
public class PublicCommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CommentDto createComment(@PathVariable Long userId,
                                    @RequestBody @Valid InputCommentDto inputCommentDto) {
        return commentService.createComment(inputCommentDto, userId, inputCommentDto.getEventId());
    }

    @PatchMapping("/{commentId}")
    public CommentDto patchComment(@PathVariable Long userId,
                                   @RequestBody InputCommentDto inputCommentDto,
                                   @PathVariable Long commentId) {
        return commentService.changeComment(inputCommentDto, userId, inputCommentDto.getEventId(), commentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long userId,
                              @PathVariable Long commentId) {
        commentService.removeByCommentIdAndAuthorId(commentId, userId);
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }
}
