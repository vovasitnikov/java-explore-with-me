package com.github.explore_with_me.main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.explore_with_me.main.category.dto.CategoryOutDto;
import com.github.explore_with_me.main.user.dto.UserShortDto;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventShortDto {

    private String annotation;
    private CategoryOutDto category;
    private Long confirmedRequests;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Long id;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private Long views;

    public EventShortDto(String annotation, CategoryOutDto category, Long confirmedRequests, LocalDateTime eventDate,
            Long id, UserShortDto initiator, boolean paid, String title) {
        this.annotation = annotation;
        this.category = category;
        this.confirmedRequests = confirmedRequests;
        this.eventDate = eventDate;
        this.id = id;
        this.initiator = initiator;
        this.paid = paid;
        this.title = title;
    }
}