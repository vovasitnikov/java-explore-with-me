package com.github.explore_with_me.main.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class CommentDto {

    private final Long          id;
    private final String        text;
    private final String        authorName;
    private final LocalDateTime created;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final LocalDateTime updated;
}