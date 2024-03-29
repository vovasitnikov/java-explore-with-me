package com.github.explore_with_me.main.event.service;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.event.dto.*;
import com.github.explore_with_me.main.event.enumerated.Sorting;
import com.github.explore_with_me.main.event.enumerated.State;
import com.github.explore_with_me.main.requests.dto.ParticipationRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventOutDto createEvent(NewEventDto newEventDto,
                            Long userId);

    List<EventShortDto> getUserEvents(Long userId,
                                      int from,
                                      int size);

    EventOutDto getUserEvent(Long userId,
                             Long eventId);

    EventOutDto patchEvent(Long userId,
                           Long eventId,
                           UpdateEventUserDto updateEventUserDto);

    List<ParticipationRequestDto> getEventRequests(Long userId,
                                                   Long eventId);

    EventRequestStatusUpdateResult changeRequestsStatus(Long userId,
                                                        Long eventId,
                                                        EventRequestStatusUpdateRequest statusUpdateRequest);

    EventOutDto publishOrCancelEvent(Long eventId,
                                     UpdateEventUserDto updateEventUserDto);

    EventOutDto getEvent(Long eventId,
                         HttpServletRequest request);

    List<EventShortDto> getEvents(HttpServletRequest request,
                                  String text,
                                  List<Long> categories,
                                  Boolean paid,
                                  LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd,
                                  boolean onlyAvailable,
                                  Sorting sort, int from, int size);

    List<EventOutDto> findEventsByAdmin(List<Long> users,
                                        List<State> states,
                                        List<Long> categories,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Integer from,
                                        Integer size);

    List<CommentDto> getEventComments(Long eventId, Integer from, Integer size);
}