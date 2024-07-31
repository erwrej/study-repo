package ru.study.microservice1.controllers;

import dtos.BrowserPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.study.microservice1.clients.StatisticsClient;
import ru.study.microservice1.service.PageServiceImpl;

@RestController
@RequestMapping("pages")
@RequiredArgsConstructor
public class PageController {
    private final PageServiceImpl pageService;
    private final StatisticsClient client;

    @GetMapping("{id}")
    public BrowserPageDto getById(@PathVariable Long id) {
        BrowserPageDto dto =  pageService.getById(id);
        client.increaseViewsKafka(dto);
        return dto;
    }

    @PostMapping
    public BrowserPageDto save(@RequestBody BrowserPageDto browserPage) {
        BrowserPageDto dto = pageService.save(browserPage);
        client.createStatisticsKafka(dto);
        return dto;
    }
}
