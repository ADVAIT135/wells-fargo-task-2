package com.example.app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_log", indexes = {
    @Index(columnList = "entity_name, entity_id", name = "idx_audit_entity")
})
public class AuditLog {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "audit_id", updatable = false, nullable = false)
    private UUID id;

    private String entityName;
    private String entityId;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private Advisor changedBy;

    private LocalDateTime changeTimestamp;

    @Column(columnDefinition = "jsonb")
    private String changeDetails;

    public AuditLog() {}

    public AuditLog(UUID id, String entityName, String entityId, ActionType actionType,
                    Advisor changedBy, LocalDateTime changeTimestamp, String changeDetails) {
        this.id = id;
        this.entityName = entityName;
        this.entityId = entityId;
        this.actionType = actionType;
        this.changedBy = changedBy;
        this.changeTimestamp = changeTimestamp;
        this.changeDetails = changeDetails;
    }

    // Getters and setters...

    public enum ActionType {
        CREATE, UPDATE, DELETE
    }
}
