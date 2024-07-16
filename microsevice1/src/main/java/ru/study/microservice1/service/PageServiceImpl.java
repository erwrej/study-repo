package ru.study.microservice1.service;

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
    public BrowserPage save(BrowserPage browserPage) {
        return pageRepository.save(browserPage);
    }

    @Transactional
    public BrowserPage getById(Long id) {
        return pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));
    }
}
