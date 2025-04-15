package com.caixamanager.repository;

import com.caixamanager.model.DailyCashSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCashSummaryRepository extends JpaRepository<DailyCashSummary, Long> {
}
