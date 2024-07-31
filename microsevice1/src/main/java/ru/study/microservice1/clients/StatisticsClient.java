package ru.study.microservice1.clients;

import dtos.BrowserPageDto;
import dtos.BrowserPageStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StatisticsClient {
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, BrowserPageDto> kafkaTemplate;

    @Value("${api.statistics.server.url}")
    private String statisticsURL;

    public void createStatistics(BrowserPageDto pageDto) {
        HttpEntity<BrowserPageDto> request = new HttpEntity<>(pageDto);
        BrowserPageStatisticsDto statisticsDto = restTemplate.postForObject(
                statisticsURL, request, BrowserPageStatisticsDto.class);
        System.out.println(statisticsDto);
    }

    public void increaseViews(BrowserPageDto pageDto) {
        HttpEntity<BrowserPageDto> request = new HttpEntity<>(pageDto);
        BrowserPageStatisticsDto statisticsDto = restTemplate.postForObject(
                statisticsURL + "/" + pageDto.getId(), request, BrowserPageStatisticsDto.class);
        System.out.println(statisticsDto);
    }

    public void createStatisticsKafka(BrowserPageDto pageDto) {
        var future = kafkaTemplate.send("stats-create-topic", "pageId=" + pageDto.getId(), pageDto);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
                System.out.println(sendResult.getProducerRecord().value());
            }
        });
    }

    public void increaseViewsKafka(BrowserPageDto pageDto) {
        var future = kafkaTemplate.send("stats-increase-topic", "pageId=" + pageDto.getId(), pageDto);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
                System.out.println(sendResult.getProducerRecord().value());
            }
        });
    }
}
