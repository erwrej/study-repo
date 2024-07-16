package ru.study.microservice1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.study.microservice1.entities.BrowserPage;
import ru.study.microservice1.service.PageServiceImpl;

@RestController
@RequestMapping("pages")
@RequiredArgsConstructor
public class PageController {
    private final PageServiceImpl pageService;

    @GetMapping("{id}")
    public BrowserPage getById(@PathVariable Long id) {
        return pageService.getById(id);
    }

    @PostMapping
    public BrowserPage save(@RequestBody BrowserPage browserPage) {
        return pageService.save(browserPage);
    }
}
