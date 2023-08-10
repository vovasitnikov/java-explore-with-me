package com.github.explore_with_me.main.comment.controller;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.dto.InputCommentDto;
import com.github.explore_with_me.main.comment.dto.UpdateCommentDto;
import com.github.explore_with_me.main.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
public class PrivateCommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CommentDto createComment(@PathVariable Long userId,
                                    @RequestBody @Valid InputCommentDto inputCommentDto) {
        return commentService.createComment(inputCommentDto, userId, inputCommentDto.getEventId());
    }

    @PatchMapping()
    public CommentDto patchComment(@PathVariable Long userId,
                                   @RequestBody UpdateCommentDto updateCommentDto) {
        return commentService.changeComment(updateCommentDto, userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long userId,
                              @PathVariable Long commentId) {
        commentService.removeByCommentIdAndAuthorId(commentId, userId);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getUserCommentsByEventId(@PathVariable Long userId, @PathVariable Long eventId) {
        return commentService.getUserCommentsByEventId(userId, eventId);
    }
}