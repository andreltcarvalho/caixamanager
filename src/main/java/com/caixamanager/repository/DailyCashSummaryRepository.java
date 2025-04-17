package com.caixamanager.repository;

import com.caixamanager.model.DailyCashSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DailyCashSummaryRepository extends JpaRepository<DailyCashSummary, Long> {
    @EntityGraph(attributePaths = "expenses")
    Optional<DailyCashSummary> findWithExpensesById(Long id);
}
