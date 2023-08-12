package com.github.explore_with_me.main.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentDto {

    Long commentId;

    @NotBlank(message = "комментарий не может быть пустым.")
    @Size(min = 10, max = 1024)
    private  String text;

    @NotNull
    @Min(0)
    private Long eventId;
}