package com.github.explore_with_me.stats.service;

import com.github.explore_with_me.stats.input_dto.InputHitDto;
import com.github.explore_with_me.stats.model.Hit;
import com.github.explore_with_me.stats.output_dto.StatsDto;
import com.github.explore_with_me.stats.repository.HitRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private HitRepository hitRepository;

    @Transactional()
    @Override
    public void saveHit(InputHitDto inputHitDto) {
        Hit hit = Hit.builder()
                .uri(inputHitDto.getUri())
                .ip(inputHitDto.getIp())
                .app(inputHitDto.getApp())
                .timestamp(inputHitDto.getTimestamp())
                .build();
        hitRepository.save(hit);
        log.info("Просмотр события с ip= " + inputHitDto.getIp() + " сохранён");
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start,
                                   LocalDateTime end,
                                   List<String> uris,
                                   boolean unique) {
        if (unique) {
            if (uris != null && !uris.isEmpty()) {
                uris.replaceAll(s -> s.replace("[", ""));
                uris.replaceAll(s -> s.replace("]", ""));
                return hitRepository.getUniqueStatsByUrisAndTimestamps(start, end, uris);
            } else {
                return hitRepository.getAllUniqueStats(start, end);
            }
        } else {
            if (uris != null && !uris.isEmpty()) {
                uris.replaceAll(s -> s.replace("[", ""));
                uris.replaceAll(s -> s.replace("]", ""));
                return hitRepository.getStatsByUrisAndTimestamps(start, end, uris);
            } else {
                return hitRepository.getAllStats(start, end);
            }
        }
    }
}
