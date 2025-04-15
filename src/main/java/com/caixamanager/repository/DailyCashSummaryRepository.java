package com.caixamanager.repository;

import com.caixamanager.model.DailyCashSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyCashSummaryRepository extends JpaRepository<DailyCashSummary, Long> {
}
