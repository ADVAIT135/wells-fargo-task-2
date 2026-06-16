# Advisor Client Portfolio Management

**A Spring Boot backend and React dashboard for managing financial advisors, clients, and client portfolios.**  
Implements a normalized relational data model with JPA entities, a clear ERD, and REST APIs to support advisor workflows and portfolio management.

---

## Overview
This repository contains the backend and supporting artifacts for a portfolio management system used by financial advisors to manage clients and client portfolios. The system uses a relational database with JPA entities, a React single-page dashboard, and Spring Boot services designed for horizontal scalability and high availability.

---

## Features
- **Advisor management** — Create, update, and remove advisors.  
- **Client management** — Each advisor can manage many clients.  
- **Portfolio management** — One portfolio per client; portfolios contain zero or more securities.  
- **Security transactions** — Add, update, and remove securities in client portfolios with purchase date, price, quantity, and currency.  
- **Audit logging** — Record create, update, and delete operations with actor and JSON change payload.  
- **Scalable architecture** — Stateless Spring services, React SPA frontend, and relational DB with read replicas.  
- **High availability** — Designed to meet a 99% uptime SLA.

---

## Tech Stack
- **Backend:** Spring Boot, Spring Data JPA, Hibernate  
- **Frontend:** React (single-page application)  
- **Database:** Relational DB (Postgres recommended)  
- **Build:** Maven  
- **Language:** Java 17+  
- **Dev Tools:** IntelliJ IDEA

---

## Getting Started

### Prerequisites
- Java 17 or later  
- Maven (`./mvnw` included)  
- Postgres or compatible RDBMS  
- Node.js and npm for the React frontend

### Quick setup
1. **Clone the repo**
   ```bash
   git clone <your-repo-url>
   cd <repo-directory>
   ```
2. **Configure database**
   - Edit `src/main/resources/application.properties` or `application.yml` with your DB credentials.
3. **Build backend**
   ```bash
   ./mvnw clean package
   ```
4. **Run backend**
   ```bash
   ./mvnw spring-boot:run
   ```
5. **Install frontend**
   ```bash
   cd frontend
   npm install
   npm start
   ```
6. **API docs**
   - Available at `/swagger-ui.html` or `/v3/api-docs` when running locally.

---

## Project Structure and ERD
**Key entities and relationships**
- **Advisor** (1 → * ) **Client**  
- **Client** (1 → 1) **Portfolio**  
- **Portfolio** (1 → * ) **PortfolioSecurity**  
- **Security** (1 → * ) **PortfolioSecurity**  
- **SecurityCategory** (1 → * ) **Security**  
- **AuditLog** references **Advisor** as the actor

**Data constraints**
- Unique constraint on advisor email.  
- Unique portfolio per client.  
- Unique portfolio security entry per `(portfolio_id, security_id, purchase_date)`.  
- Monetary precision using `DECIMAL(18,4)` and quantities using `DECIMAL(18,6)`.

---

## Data Model Key Entities

**Advisor**  
- `advisor_id` (PK)  
- `first_name`, `last_name`, `email` (unique), `phone`, `address`  
- `created_at`, `updated_at`

**Client**  
- `client_id` (PK)  
- `advisor_id` (FK)  
- `first_name`, `last_name`, `email`, `phone`, `date_of_birth`, `address`  
- `created_at`, `updated_at`

**Portfolio**  
- `portfolio_id` (PK)  
- `client_id` (FK, unique)  
- `name`, `created_at`, `updated_at`

**Security**  
- `security_id` (PK)  
- `ticker`, `name`, `isin`, `category_id` (FK)  
- `created_at`, `updated_at`

**SecurityCategory**  
- `category_id` (PK)  
- `name`, `description`

**PortfolioSecurity**  
- `portfolio_security_id` (PK)  
- `portfolio_id` (FK), `security_id` (FK)  
- `purchase_date`, `purchase_price DECIMAL(18,4)`, `quantity DECIMAL(18,6)`, `currency`  
- `created_at`, `updated_at`  
- Unique constraint: `(portfolio_id, security_id, purchase_date)`

**AuditLog**  
- `audit_id` (PK)  
- `entity_name`, `entity_id`, `action_type` (CREATE/UPDATE/DELETE)  
- `changed_by` (advisor_id FK), `change_timestamp`, `change_details` (JSONB)

---

## Implementation Notes
- Entities implemented as JPA `@Entity` classes with generated IDs and appropriate `@OneToMany`, `@ManyToOne`, and `@OneToOne` mappings.  
- Use `@PrePersist` and `@PreUpdate` lifecycle hooks to maintain `createdAt` and `updatedAt` timestamps.  
- Consider `com.vladmihalcea:hibernate-types-52` for JSONB mapping if you want `changeDetails` to map to a `Map` or typed object.  
- Keep entities separate from API DTOs to avoid overexposing internal relationships to the frontend.  
- Add indexes on frequently queried columns (ticker, isin, advisor.email, portfolio/security FKs).  
- Use stateless services and horizontal app servers for scalability; use DB read replicas and automated failover for availability.

---

## Skills Learned

- **Data Modeling**  
- **Database Design**  
- **Java Persistence**  
- **Java Programming**  
- **Spring Framework**  
- **Systems Design**

---

---

## License
This project is provided for learning and demonstration purposes. Add a license file as appropriate for your organization.

---

## Contact
For questions about the implementation or to request API endpoints and example payloads, open an issue in this repository.
