package ru.study.microservice2.services;

import dtos.BrowserPageDto;
import dtos.BrowserPageStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.microservice2.entities.BrowserPageStatistics;
import ru.study.microservice2.repositories.StatisticsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    @Transactional
    public BrowserPageStatisticsDto create(BrowserPageDto dto) {
        BrowserPageStatistics statistics = new BrowserPageStatistics(dto);
        BrowserPageStatistics savedStatistics = statisticsRepository.save(statistics);
        return mapToDto(savedStatistics);
    }

    @Transactional
    public BrowserPageStatisticsDto increaseViews(BrowserPageDto dto) {
        BrowserPageStatistics statistics = statisticsRepository.findByPageId(dto.getId())
                .orElseThrow(() -> new RuntimeException("Statistics not found"));
        statistics.increaseViews();
        return mapToDto(statistics);
    }

    @Transactional
    public BrowserPageStatisticsDto getByPageId(Long pageId) {
        return statisticsRepository.findByPageId(pageId)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Statistics not found"));
    }

    @Transactional
    public List<BrowserPageStatisticsDto> getByAll() {
        return statisticsRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private BrowserPageStatisticsDto mapToDto(BrowserPageStatistics statistics) {
        return new BrowserPageStatisticsDto(statistics.getPageId(), statistics.getViews());
    }
}
