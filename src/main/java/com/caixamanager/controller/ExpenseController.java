package com.caixamanager.controller;

import com.caixamanager.dto.CreateExpenseDTO;
import com.caixamanager.model.Expense;
import com.caixamanager.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        List<Expense> expenses = expenseService.findAll();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getById(@PathVariable Long id) {
        return expenseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        Expense created = expenseService.create(expense);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable Long id, @RequestBody Expense expense) {
        Expense updated = expenseService.update(id, expense);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/summary")
    public ResponseEntity<Expense> getSummary(@RequestBody CreateExpenseDTO createExpenseDTO) {
        Expense summary = expenseService.createExpenses(createExpenseDTO);
        return ResponseEntity.ok(summary);
    }
}
