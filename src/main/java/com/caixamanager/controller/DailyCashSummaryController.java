package com.caixamanager.controller;

import com.caixamanager.model.DailyCashSummary;
import com.caixamanager.repository.DailyCashSummaryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/daily-cash-summary")
public class DailyCashSummaryController {

    private final DailyCashSummaryRepository repository;

    public DailyCashSummaryController(DailyCashSummaryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DailyCashSummary> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<DailyCashSummary> create(@Valid @RequestBody DailyCashSummary summary) {
        return ResponseEntity.ok(repository.save(summary));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyCashSummary> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyCashSummary> update(@PathVariable Long id, @Valid @RequestBody DailyCashSummary updatedSummary) {
        return repository.findById(id).map(existingSummary -> {
            updatedSummary.setId(existingSummary.getId());
            return ResponseEntity.ok(repository.save(updatedSummary));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/net-cash-flow")
    public BigDecimal getTotalNetCashFlow() {
        return repository.findAll().stream()
                .map(DailyCashSummary::getNetCashFlow)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
