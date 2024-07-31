package ru.study.microservice1.service;

import dtos.BrowserPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.microservice1.entities.BrowserPage;
import ru.study.microservice1.repositories.PageRepository;

@Service
@RequiredArgsConstructor
public class PageServiceImpl {
    private final PageRepository pageRepository;

    @Transactional
    public BrowserPageDto save(BrowserPageDto dto) {
        BrowserPage pageToSave = new BrowserPage(dto);
        BrowserPage newPage = pageRepository.save(pageToSave);
        return mapToDto(newPage);
    }

    @Transactional
    public BrowserPageDto getById(Long id) {
        return pageRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Page not found"));
    }

    private BrowserPageDto mapToDto(BrowserPage browserPage) {
        return new BrowserPageDto(browserPage.getId(), browserPage.getContent());
    }
}
