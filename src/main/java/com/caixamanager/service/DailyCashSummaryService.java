package com.caixamanager.service;

import com.caixamanager.mapper.DTOMapper;
import com.caixamanager.model.DailyCashSummary;
import com.caixamanager.repository.DailyCashSummaryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DailyCashSummaryService {

    private final DailyCashSummaryRepository repository;

    public List<DailyCashSummary> findAll() {
        return repository.findAll();
    }

    @Transactional
    public DTOMapper.DailyCashSummaryDTO findById(Long id) {
        var summary= repository.findWithExpensesById(id).orElseThrow(()->
                new RuntimeException("DailyCashSummary not found"));
        return DTOMapper.toDTO(summary);
    }


    public DailyCashSummary create(DailyCashSummary summary) {
        return repository.saveAndFlush(summary);
    }

    public DailyCashSummary update(Long id, DailyCashSummary summary) {
        summary.setId(id);
        return repository.save(summary);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
