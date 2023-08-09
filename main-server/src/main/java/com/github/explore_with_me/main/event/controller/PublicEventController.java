package com.github.explore_with_me.main.event.controller;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.event.dto.EventOutDto;
import com.github.explore_with_me.main.event.dto.EventShortDto;
import com.github.explore_with_me.main.event.enumerated.Sorting;
import com.github.explore_with_me.main.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class PublicEventController {

//    private final StatsClient statsClient;

    private final EventService eventService;

    @GetMapping("/{id}")
    public EventOutDto getEvent(@PathVariable Long id,
                                HttpServletRequest request) {
//        statsClient.saveHit(new InputHitDto("explore_with_me_main", request.getRequestURI(),
//                request.getRemoteAddr(),
//                LocalDateTime.now()));
        return eventService.getEvent(id, request);
    }

    @GetMapping
    public List<EventShortDto> getEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "false") boolean onlyAvailable,
            @RequestParam(required = false) Sorting sort,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
//        statsClient.saveHit(new InputHitDto("explore_with_me_main", request.getRequestURI(),
//                request.getRemoteAddr(),
//                LocalDateTime.now()));
        return eventService.getEvents(request, text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getComments(@PathVariable Long id,
                                        @RequestParam(defaultValue = "0") int from,
                                        @RequestParam(defaultValue = "10") int size) {
        return eventService.getEventComments(id, from, size);
    }
}