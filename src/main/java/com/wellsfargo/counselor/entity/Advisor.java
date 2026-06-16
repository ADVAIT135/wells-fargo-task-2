package com.wellsfargo.counselor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import java.time.LocalDateTime;

@Entity
@Table(name = "advisor", indexes = {
    @Index(columnList = "email", name = "idx_advisor_email")
})
public class Advisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advisor_id", updatable = false, nullable = false)
    private Long advisorId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected Advisor() {
        // JPA requires a no-arg constructor
    }

    /**
     * All-args constructor (including id) to satisfy "initialize all instance variables" requirement.
     * Use this when you need to construct a fully-populated instance (e.g., in tests or migrations).
     */
    public Advisor(Long advisorId,
                   String firstName,
                   String lastName,
                   String address,
                   String phone,
                   String email,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.advisorId = advisorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Convenience constructor without id (typical for creating new entities).
     */
    public Advisor(String firstName,
                   String lastName,
                   String address,
                   String phone,
                   String email) {
        this(null, firstName, lastName, address, phone, email, LocalDateTime.now(), null);
    }

    public Long getAdvisorId() {
        return advisorId;
    }

    // No setter for advisorId is required; include only if you need to set IDs manually in tests.
    public void setAdvisorId(Long advisorId) {
        this.advisorId = advisorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
