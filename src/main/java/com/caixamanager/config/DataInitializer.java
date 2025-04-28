package com.caixamanager.config;

import com.caixamanager.model.Company;
import com.caixamanager.model.DailyCashSummary;
import com.caixamanager.model.Expense;
import com.caixamanager.service.CompanyService;
import com.caixamanager.service.DailyCashSummaryService;
import com.caixamanager.service.ExpenseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CompanyService companyService;
    private final ExpenseService expenseService;
    private final DailyCashSummaryService dailyCashSummaryService;

    public DataInitializer(CompanyService companyService, ExpenseService expenseService, DailyCashSummaryService dailyCashSummaryService) {
        this.companyService = companyService;
        this.expenseService = expenseService;
        this.dailyCashSummaryService = dailyCashSummaryService;
    }

    @Override
    public void run(String... args) {

        // Cria empresa padrão
        Company company = new Company();
        company.setName("gardenia");
        companyService.create(company);
        System.out.println("Empresa criada: " + company.getName());

        // Cria despesa associada à empresa
        Expense expense = new Expense();
        expense.setAmount(new BigDecimal("100.00"));
        expense.setExchange(new BigDecimal("100.00"));
        expense.setDescription("Despesa inicial");
        expense.setCompany(company);
        expense = expenseService.create(expense);
        System.out.println("Despesa criada para a empresa: " + company.getName());

        // Cria DailyCashSummary se não existir para hoje
        DailyCashSummary summary = new DailyCashSummary();
        summary.setDate(LocalDate.now());
        summary.setExpenses(Set.of(expense));
        summary = dailyCashSummaryService.create(summary);
        System.out.println("DailyCashSummary de hoje criado.");
    }
}
