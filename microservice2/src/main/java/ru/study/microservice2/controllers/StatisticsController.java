package ru.study.microservice2.controllers;

import dtos.BrowserPageDto;
import dtos.BrowserPageStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.study.microservice2.services.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("stats")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    public List<BrowserPageStatisticsDto> getAll() {
        return statisticsService.getByAll();
    }

    @GetMapping("{pageId}")
    public BrowserPageStatisticsDto getByPageId(@PathVariable Long pageId) {
        return statisticsService.getByPageId(pageId);
    }

    @PostMapping
    public BrowserPageStatisticsDto create(@RequestBody BrowserPageDto dto) {

        return statisticsService.create(dto);
    }

    @PostMapping("{pageId}")
    public BrowserPageStatisticsDto increaseViews(@RequestBody BrowserPageDto dto,
                                                  @PathVariable Long pageId) {
        return statisticsService.increaseViews(dto);
    }
}
