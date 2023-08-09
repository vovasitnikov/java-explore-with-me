package com.github.explore_with_me.main.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCommentDto {

    @NotBlank(message = "комментарий не может быть пустым.")
    @Size(min = 10, max = 1024)
    private  String text;
    @NotNull
    @Min(0)
    private Long eventId;
}