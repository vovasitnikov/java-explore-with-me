package com.github.explore_with_me.main.event.controller;

import com.github.explore_with_me.main.event.dto.*;
import com.github.explore_with_me.main.event.service.EventService;
import com.github.explore_with_me.main.exception.model.BadRequestException;
import com.github.explore_with_me.main.requests.dto.ParticipationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController()
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventController {

    private final EventService eventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public EventOutDto createEvent(@RequestBody @Valid NewEventDto newEventDto,
                                   @PathVariable Long userId) {
        dateTimeValidate(newEventDto.getEventDate());
        return eventService.createEvent(newEventDto, userId);

    }

    @GetMapping
    public List<EventShortDto> getUsersEvents(@PathVariable Long userId,
                                              @RequestParam(defaultValue = "0") int from,
                                              @RequestParam(defaultValue = "10") int size) {
        return eventService.getUserEvents(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventOutDto getUserEvent(@PathVariable Long userId,
                                    @PathVariable Long eventId) {
        return eventService.getUserEvent(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventOutDto patchEvent(@PathVariable Long userId,
                                  @PathVariable Long eventId,
                                  @RequestBody @Valid UpdateEventUserDto updateEventUserDto) {
        if (updateEventUserDto.getEventDate() != null) {
            dateTimeValidate(updateEventUserDto.getEventDate());
        }
        return eventService.patchEvent(userId, eventId, updateEventUserDto);
    }

    @GetMapping("{eventId}/requests")
    public List<ParticipationRequestDto> getEventRequests(@PathVariable Long userId,
                                                          @PathVariable Long eventId) {
        return eventService.getEventRequests(userId, eventId);
    }

    @PatchMapping("{eventId}/requests")
    public EventRequestStatusUpdateResult changeRequestsStatus(@PathVariable Long userId,
                                                               @PathVariable Long eventId,
                                                               @RequestBody EventRequestStatusUpdateRequest statusUpdateRequest) {
        return eventService.changeRequestsStatus(userId, eventId, statusUpdateRequest);
    }

    private void dateTimeValidate(LocalDateTime localDateTime) {
        if (localDateTime.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new BadRequestException(
                    "Вы не можете добавить событие, которое проходит раньше чем за два часа от текущей даты "
                            + localDateTime);
        }
    }
}