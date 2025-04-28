package com.caixamanager.service;

import com.caixamanager.model.Company;
import com.caixamanager.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return repository.findById(id);
    }

    public Company create(Company company) {
        return repository.save(company);
    }

    public Company update(Long id, Company company) {
        company.setId(id);
        return repository.save(company);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Company findByName(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new RuntimeException("Company not found"));
    }
}
