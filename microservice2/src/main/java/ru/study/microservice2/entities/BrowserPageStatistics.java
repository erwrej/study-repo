package ru.study.microservice2.entities;

import dtos.BrowserPageDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserPageStatistics {
    @Id
    Long pageId;
    Long views;

    public BrowserPageStatistics(BrowserPageDto dto) {
        this.pageId = dto.getId();
        this.views = 0L;
    }

    public void increaseViews() {
        this.views += 1;
    }
}
