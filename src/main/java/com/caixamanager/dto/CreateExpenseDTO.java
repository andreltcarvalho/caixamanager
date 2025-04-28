package com.caixamanager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateExpenseDTO {
    private String company;
    private String description;
    private BigDecimal value;
    private BigDecimal exchange;
}
