package ru.study.microservice1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.microservice1.entities.BrowserPage;

@Repository
public interface PageRepository extends JpaRepository<BrowserPage, Long> {
}
