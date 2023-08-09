package com.github.explore_with_me.main.comment.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCommentDto {

    @NotBlank(message = "комментарий не может быть пустым.")
    @Min(10)
    @Max(1024)
    private  String text;
    @NotNull
    @Min(0)
    private Long eventId;
}