package com.caixamanager.mapper;

import com.caixamanager.model.Company;
import com.caixamanager.model.DailyCashSummary;
import com.caixamanager.model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for mapping model objects to Data Transfer Objects (DTOs).
 */
public class DTOMapper {

    /**
     * Data Transfer Object (DTO) representation of a Company.
     *
     * @param id   the unique identifier of the company
     * @param name the name of the company
     */
    public record CompanyDTO(Long id, String name) {
    }

    /**
     * Data Transfer Object (DTO) representation of an Expense.
     *
     * @param id          the unique identifier of the expense
     * @param description the description of the expense
     * @param amount      the monetary amount of the expense
     * @param company     the company associated with the expense
     */
    public record ExpenseDTO(Long id, String description, BigDecimal amount, CompanyDTO company) {
    }

    /**
     * Data Transfer Object (DTO) representation of a DailyCashSummary.
     *
     * @param id       the unique identifier of the daily cash summary
     * @param date     the date for which the summary applies
     * @param expenses the list of expenses for the given day
     */
    public record DailyCashSummaryDTO(Long id, LocalDate date, List<ExpenseDTO> expenses) {
    }

    /**
     * Converts a Company model to a CompanyDTO.
     *
     * @param company the Company model to convert
     * @return the corresponding CompanyDTO, or null if the input is null
     */
    public static CompanyDTO toDTO(Company company) {
        if (company == null) return null;
        return new CompanyDTO(company.getId(), company.getName());
    }

    /**
     * Converts an Expense model to an ExpenseDTO.
     *
     * @param expense the Expense model to convert
     * @return the corresponding ExpenseDTO, or null if the input is null
     */
    public static ExpenseDTO toDTO(Expense expense) {
        if (expense == null) return null;
        return new ExpenseDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                toDTO(expense.getCompany())
        );
    }

    /**
     * Converts a DailyCashSummary model to a DailyCashSummaryDTO.
     *
     * @param summary the DailyCashSummary model to convert
     * @return the corresponding DailyCashSummaryDTO, or null if the input is null
     */
    public static DailyCashSummaryDTO toDTO(DailyCashSummary summary) {
        if (summary == null) return null;
        List<ExpenseDTO> expenseDTOs = summary.getExpenses()
                .stream()
                .map(DTOMapper::toDTO)
                .toList();

        return new DailyCashSummaryDTO(summary.getId(), summary.getDate(), expenseDTOs);
    }
}