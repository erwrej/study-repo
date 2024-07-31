package ru.study.microservice2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.microservice2.entities.BrowserPageStatistics;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<BrowserPageStatistics, Long> {
    Optional<BrowserPageStatistics> findByPageId(Long pageId);
}
