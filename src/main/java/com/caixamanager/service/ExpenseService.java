package com.caixamanager.service;

import com.caixamanager.model.Expense;
import com.caixamanager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Optional<Expense> findById(Long id) {
        return repository.findById(id);
    }

    public Expense create(Expense expense) {
        return repository.save(expense);
    }

    public Expense update(Long id, Expense expense) {
        expense.setId(id);
        return repository.save(expense);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
