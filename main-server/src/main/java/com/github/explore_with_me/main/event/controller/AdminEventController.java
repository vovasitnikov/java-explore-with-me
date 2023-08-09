package com.github.explore_with_me.main.event.controller;

import com.github.explore_with_me.main.event.dto.EventOutDto;
import com.github.explore_with_me.main.event.dto.UpdateEventUserDto;
import com.github.explore_with_me.main.event.enumerated.State;
import com.github.explore_with_me.main.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {

    private final EventService eventService;

    @PatchMapping("/{eventId}")
    public EventOutDto publishOrCancelEvent(@PathVariable Long eventId,
                                            @RequestBody @Valid UpdateEventUserDto updateEventUserDto) {
        return eventService.publishOrCancelEvent(eventId, updateEventUserDto);
    }

    @GetMapping
    public List<EventOutDto> findEvents(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<State> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size) {
        return eventService.findEventsByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }
}