package com.caixamanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "daily_cash_summary") // Define o nome da tabela no banco de dados
@Data
public class DailyCashSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private String companyName;

    @NotNull
    private BigDecimal incoming;

    @NotNull
    private BigDecimal outcoming;

    private BigDecimal otherExpenses;

    @Transient
    public BigDecimal getNetCashFlow() {
        return incoming.subtract(outcoming).subtract(otherExpenses != null ? otherExpenses : BigDecimal.ZERO);
    }
}

