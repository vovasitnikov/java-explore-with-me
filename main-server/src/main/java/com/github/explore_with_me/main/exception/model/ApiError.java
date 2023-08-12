package com.github.explore_with_me.main.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String status;
    private String reason;
    private String message;
    private LocalDateTime timestamp;
}
