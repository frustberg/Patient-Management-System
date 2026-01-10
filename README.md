# 🏥 Patient Management Microservices Platform

A full-stack, cloud-ready **patient management platform** built with **Java + Spring Boot**, featuring **microservices**, **gRPC communication**, **JWT-based authentication**, **API Gateway routing**, **analytics**, and **billing workflows** — all orchestrated via **Docker Compose** for local development.

---

## 📦 Core Features

- Patient onboarding & profile management
- JWT-based authentication & role handling
- Billing pipeline with invoicing & payments
- Analytics aggregation & reporting
- REST + gRPC hybrid service communication
- Central API Gateway routing
- Centralized configuration & service discovery
- Integration tests for workflows
- Dockerized infrastructure for fast spin-up

---

## 🏗️ System Architecture Overview

```
                 ┌──────────────────────────┐
                 │        Client/UI         │
                 └─────────────┬────────────┘
                               │ REST
                               ▼
                       ┌───────────────┐
                       │ API Gateway   │
                       └───────┬───────┘
            ┌──────────────────┼──────────────────┐
            │                  │                  │
   REST     │          REST    │         gRPC     │
            ▼                  ▼                  ▼
┌────────────────┐   ┌────────────────┐   ┌───────────────────┐
│ Auth Service   │   │ Patient Service│   │ Billing Service   │
└────────────────┘   └────────────────┘   └───────────────────┘
                                           │
                                           │ gRPC
                                           ▼
                                  ┌────────────────┐
                                  │ Analytics Svc  │
                                  └────────────────┘

```

---

## 🧩 Microservices Breakdown

| Service | Protocol | Purpose |
|---|---|---|
| api-gateway | REST | Gateway routing, public entrypoint |
| auth-service | REST | User auth, JWT issuance, roles |
| patient-service | REST/gRPC | Patient CRUD & clinical data |
| billing-service | gRPC | Invoicing, charges, transactions |
| analytics-service | gRPC | Aggregates metrics for BI |
| integration-tests | REST/gRPC | End-to-end workflow testing |
| infrastructure | N/A | Docker, DB volumes, scripts |

---

## 📂 Project Structure

```
Patient-Management-Service_Completed/
├── api-gateway/
├── auth-service/
├── patient-service/
├── billing-service/
├── analytics-service/
├── infrastructure/
├── integration-tests/
├── grpc-requests/
├── api-requests/
└── db_volumes/
```

---

## 🔐 Authentication Flow (Simplified)

1. Client logs in via `/auth/login`
2. Auth service validates credentials
3. Issues JWT with roles (doctor/admin/etc.)
4. API Gateway validates JWT on every request
5. Downstream services trust gateway → user context is propagated

---

## 💳 Billing Flow (Simplified)

1. Patient event triggers billing
2. patient-service calls billing-service via gRPC
3. Billing computes invoice, applies fees, saves records
4. analytics-service subscribes for metrics

---

## 📊 Analytics Flow (Simplified)

- Billing emits revenue events
- Analytics aggregates:
  - revenue
  - patient counts
  - invoice frequency
  - aged receivables

---

## 🚀 Running the Project Locally

### Prerequisites

- Docker & Docker Compose
- Java 17+ (optional)
- Maven 3.8+ (optional)

---

## 🐳 Option A: Run Everything via Docker Compose

From project root:

```sh
docker compose up --build
```

Access services:

| Component | URL |
|---|---|
| API Gateway | http://localhost:8080 |
| Auth Service | http://localhost:8081 |
| Patient Service | http://localhost:8082 |
| Billing Service | internal gRPC |
| Analytics Service | internal gRPC |

---

## 🧪 Option B: Run Services Manually

Start infra first:

```sh
docker compose -f infrastructure/docker-compose.yml up -d
```

Run service:

```sh
mvn spring-boot:run
```

---

## 📚 API Documentation

### Auth

| Method | Endpoint | Description |
|---|---|---|
| POST | /auth/register | Register user |
| POST | /auth/login | Login + JWT |

### Patient

| Method | Endpoint | Description |
|---|---|---|
| POST | /patients | Create patient |
| GET | /patients/{id} | Fetch patient |
| PUT | /patients/{id} | Update patient |

### Billing (gRPC)

.proto files:

```
grpc-requests/billing/*.proto
```

---

## 🧰 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- gRPC
- PostgreSQL
- Docker / Docker Compose
- Maven

---

## 📦 Production Deployment Notes

Recommended add-ons:

- API rate limiting
- Centralized logs (ELK/Loki)
- Distributed tracing (OTel/Jaeger)
- Circuit breaking (Resilience4J)
- Service Mesh (Istio/Linkerd)
- DB migrations (Flyway/Liquibase)

---

## 🤝 Contributing

1. Fork
2. Create feature branch
3. Submit PR


