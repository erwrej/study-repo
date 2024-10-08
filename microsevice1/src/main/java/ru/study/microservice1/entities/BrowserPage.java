package ru.study.microservice1.entities;

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
public class BrowserPage {
    @Id
    private Long id;
    private String content;

    public BrowserPage(BrowserPageDto dto) {
        this.id = dto.getId();
        this.content = dto.getContent();
    }
}
