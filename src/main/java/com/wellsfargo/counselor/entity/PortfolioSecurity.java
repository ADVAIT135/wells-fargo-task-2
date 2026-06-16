package com.example.app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "portfolio_security", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"portfolio_id", "security_id", "purchase_date"})
})
public class PortfolioSecurity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "portfolio_security_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_id", nullable = false)
    private Security security;

    private LocalDate purchaseDate;

    @Column(precision = 18, scale = 4)
    private BigDecimal purchasePrice;

    @Column(precision = 18, scale = 6)
    private BigDecimal quantity;

    @Column(length = 3)
    private String currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PortfolioSecurity() {}

    public PortfolioSecurity(UUID id, Portfolio portfolio, Security security, LocalDate purchaseDate,
                             BigDecimal purchasePrice, BigDecimal quantity, String currency,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.portfolio = portfolio;
        this.security = security;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters...
}
