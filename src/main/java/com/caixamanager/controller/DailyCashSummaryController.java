package com.caixamanager.controller;

import com.caixamanager.mapper.DTOMapper;
import com.caixamanager.model.DailyCashSummary;
import com.caixamanager.service.DailyCashSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daily-cash-summaries")
public class DailyCashSummaryController {

    private final DailyCashSummaryService service;

    public DailyCashSummaryController(DailyCashSummaryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DailyCashSummary>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOMapper.DailyCashSummaryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public DailyCashSummary create(@RequestBody DailyCashSummary summary) {
        return service.create(summary);
    }

    @PutMapping("/{id}")
    public DailyCashSummary update(@PathVariable Long id, @RequestBody DailyCashSummary summary) {
        return service.update(id, summary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
