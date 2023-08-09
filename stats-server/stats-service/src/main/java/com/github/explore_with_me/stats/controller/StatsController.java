package com.github.explore_with_me.stats.controller;


import com.github.explore_with_me.stats.exception.BadRequestException;
import com.github.explore_with_me.stats.input_dto.InputHitDto;
import com.github.explore_with_me.stats.output_dto.StatsDto;
import com.github.explore_with_me.stats.service.StatsService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hit")
    public void saveHit(@RequestBody InputHitDto inputHitDto) {
        statsService.saveHit(inputHitDto);
    }

    @GetMapping("/stats")
    public List<StatsDto> getStats(
            @RequestParam  String start,
            @RequestParam  String end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") boolean unique) {
        LocalDateTime startParsed = LocalDateTime.parse(URLDecoder.decode(start, StandardCharsets.UTF_8),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endParsed = LocalDateTime.parse(URLDecoder.decode(end, StandardCharsets.UTF_8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        validateDates(startParsed, endParsed);
        return statsService.getStats(startParsed, endParsed, uris, unique);
    }

    private void validateDates(LocalDateTime start,
                               LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new BadRequestException();
        }
    }
}
