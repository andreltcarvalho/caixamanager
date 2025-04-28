package com.caixamanager.mapper;

import com.caixamanager.dto.CreateExpenseDTO;
import com.caixamanager.model.Expense;
import com.caixamanager.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExpenseDTOMapper {
    private final CompanyService companyService;
    public Expense fromDTO(CreateExpenseDTO createExpenseDTO){
        Expense expense = new Expense();
        expense.setDescription(createExpenseDTO.getDescription());
        expense.setAmount(createExpenseDTO.getValue());
        expense.setExchange(createExpenseDTO.getExchange());

        expense.setCompany(companyService.findByName(createExpenseDTO.getCompany()));

        return expense;
    }
}
