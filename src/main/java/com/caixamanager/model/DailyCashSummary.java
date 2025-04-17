package com.caixamanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "daily_cash_summary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class DailyCashSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "expense_daily_cash_summary",
            joinColumns = @JoinColumn(name = "daily_cash_summary_id"),
            inverseJoinColumns = @JoinColumn(name = "expense_id")
    )
    private Set<Expense> expenses = new HashSet<>();
}

