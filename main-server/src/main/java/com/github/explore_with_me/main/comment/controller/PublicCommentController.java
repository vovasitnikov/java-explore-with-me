package com.github.explore_with_me.main.comment.controller;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.service.CommentService;
import com.github.explore_with_me.main.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("comments")
@RequiredArgsConstructor
public class PublicCommentController {

    private final EventService   eventService;
    private final CommentService commentService;

    @GetMapping("/events/{id}")
    public List<CommentDto> getComments(@PathVariable Long id,
                                        @RequestParam(defaultValue = "0") int from,
                                        @RequestParam(defaultValue = "10") int size) {
        return eventService.getEventComments(id, from, size);
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }
}
