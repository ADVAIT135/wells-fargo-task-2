package com.example.app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "security", indexes = {
    @Index(columnList = "ticker", name = "idx_security_ticker"),
    @Index(columnList = "isin", name = "idx_security_isin")
})
public class Security {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "security_id", updatable = false, nullable = false)
    private UUID id;

    private String ticker;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private SecurityCategory category;

    private String isin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "security", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PortfolioSecurity> portfolioEntries = new HashSet<>();

    public Security() {}

    public Security(UUID id, String ticker, String name, SecurityCategory category, String isin,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.ticker = ticker;
        this.name = name;
        this.category = category;
        this.isin = isin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters...
}
