package ru.study.microservice2.listeners;

import dtos.BrowserPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.study.microservice2.services.StatisticsService;

@Service
@RequiredArgsConstructor
public class StatisticsListener {
    private final StatisticsService statisticsService;

    @KafkaListener(topics = {"stats-increase-topic"})
    public void increaseViews(BrowserPageDto browserPageDto) {
        System.out.println(statisticsService.increaseViews(browserPageDto));
    }

    @KafkaListener(topics = {"stats-create-topic"})
    public void create(BrowserPageDto browserPageDto) {
        System.out.println(statisticsService.create(browserPageDto));
    }
}
