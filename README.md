# Airline Booking System GDS Microservices

A multi-service airline booking platform built with Java 21, Spring Boot 4, and Maven. The project is organized as a modular microservice architecture where each business capability is separated into its own service.

This README is intended to stay current as each service is developed. When a new module is completed, add its responsibilities, endpoints, and database details here.

## 1. Project Goal

The system models a modern airline distribution platform with these core business areas:

- airline and aircraft management
- city and airport master data
- flight and schedule operations
- fare and baggage pricing
- seat inventory and cabin classes
- user management and authentication

The goal is to build a scalable, maintainable architecture where services can evolve independently while sharing common DTOs, enums, and reusable project types through a common library.

## 2. High-Level Architecture

The project follows a service-oriented architecture with a shared library and independent business services.

```text
+-----------------------------------------------------------+
|                      Airline Booking System                 |
|                                                           |
|  +------------------+   +------------------------------+  |
|  | common-lib       |   |        cloud/infra           |  |
|  | - shared DTOs    |   | (future: registry/config/gw)|  |
|  | - enums          |   |                              |  |
|  | - request/resp   |   +------------------------------+  |
|  +------------------+                                      |
|                                                            |
|  +-------------------+   +-------------------+              |
|  | user-service      |   | airline-core      |              |
|  | - auth/login      |   | - airline info    |              |
|  | - user profile    |   | - aircraft        |              |
|  +-------------------+   +-------------------+              |
|                                                            |
|  +-------------------+   +-------------------+              |
|  | location-service  |   | flight-ops-service|              |
|  | - cities          |   | - flights         |              |
|  | - airports        |   | - schedules       |              |
|  +-------------------+   | - instances       |              |
|                            +-------------------+              |
|                                                            |
|  +-------------------+   +-------------------+              |
|  | pricing-service   |   | seat-service      |              |
|  | - fares           |   | - cabin classes   |              |
|  | - baggage rules  |   | - seat config     |              |
|  +-------------------+   +-------------------+              |
|                                                            |
|  MySQL Databases (separate per service)                   |
+-----------------------------------------------------------+
```

## 3. Project Structure

```text
microservies/
├── README.md
├── pom.xml                              # parent Maven configuration
├── common-lib/                          # shared library
│   └── src/main/java/com/microservice/airline/
│       ├── enums/
│       ├── payload/request/
│       ├── payload/response/
│       ├── payload/dto/
│       └── embeddable/
├── cloud/                               # infrastructure modules (future)
│   └── pom.xml
├── services/
│   ├── pom.xml                          # service aggregator
│   ├── user-service/
│   │   ├── src/main/java/.../controller/
│   │   ├── src/main/java/.../service/
│   │   ├── src/main/java/.../model/
│   │   ├── src/main/java/.../repository/
│   │   └── src/main/resources/application.yaml
│   ├── airline-core-service/
│   ├── location-service/
│   ├── flight-ops-service/
│   ├── pricing-service/
│   └── seat-service/
└── .git/
```

## 4. Root Build Setup

This project uses a parent Maven project with child modules.

Main root POM details:

- Java version: 21
- Spring Boot version: 4.1.0
- Spring Cloud version: 2025.1.2
- Packaging: pom (aggregator project)

The root pom.xml includes the following modules:

- common-lib
- cloud
- services

The services aggregator contains the actual business services.

## 5. Shared Library (common-lib)

The common-lib module is central to the project. It contains shared code reused across microservices.

Included items:

- enums such as AirlineStatus, FlightStatus, CabinClassType, UserRole
- request DTOs for creating and updating entities
- response DTOs for API output
- common embedded value objects (Address, GeoCode, etc.)
- shared user payloads and auth response structures

This reduces duplication and keeps the service contracts consistent.

## 6. Services Implemented So Far

### 6.1 user-service

Purpose:
- user registration and authentication
- login flow using JWT
- profile retrieval and user management
- role-based access support

Key package areas:
- controller/AuthController
- controller/UserController
- config/JwtProvider
- config/SecurityConfig
- service/AuthService and Impl
- service/UserService and Impl
- model/User
- repository/UserRepository

Default configuration:

- port: 5001
- database: airline_user_service_db

Main auth endpoints:

- POST /auth/signup
- POST /auth/login

User endpoints:

- GET /api/users
- GET /api/users/{userId}
- GET /api/users/profile

### 6.2 airline-core-service

Purpose:
- airline profile management
- aircraft metadata management
- owner/admin airline status workflow

Key package areas:
- controller/AirlineController
- controller/AircraftController
- service/AirlineService and Impl
- service/AircraftService and Impl
- model/Airline
- model/Aircraft
- repository/AirlineRepository
- repository/AircraftRepository

Default configuration:

- port: 5002
- database: airline_core_airline_db

Main endpoints:

- POST /api/airlines
- GET /api/airlines/{id}
- GET /api/airlines
- GET /api/airlines/admin
- PUT /api/airlines/{id}/approve
- PUT /api/airlines/{id}/suspend
- PUT /api/airlines/{id}/ban

### 6.3 location-service

Purpose:
- maintain cities and airports
- support search and filtering by country or city

Key package areas:
- controller/CityController
- controller/AirportController
- service/CityService and Impl
- service/AirportService and Impl
- model/City
- model/Airport
- repository/CityRepository
- repository/AirportRepository

Default configuration:

- port: 5004
- database: airline_location_db

Main endpoints:

- POST /api/cities
- GET /api/cities
- GET /api/cities/{id}
- GET /api/cities/search
- GET /api/cities/country/{countryCode}
- POST /api/airports
- GET /api/airports
- GET /api/airports/{id}
- GET /api/airports/city/{cityId}

### 6.4 flight-ops-service

Purpose:
- flight master data
- flight schedules
- flight instances and operational lifecycle

Key package areas:
- controller/FlightController
- controller/FlightScheduleController
- controller/FlightInstanceController
- service/FlightService and Impl
- service/FlightScheduleService and Impl
- service/FlightInstanceService and Impl
- model/Flight
- model/FlightSchedule
- model/FlightInstance

Default configuration:

- port: 5005
- database: airline_flight_db

Main endpoints:

- POST /api/flights
- GET /api/flights/{id}
- GET /api/flights/airline
- PUT /api/flights/{id}
- PATCH /api/flights/{id}/status
- DELETE /api/flights/{id}

### 6.5 pricing-service

Purpose:
- fare definition and optimization
- baggage policy management
- fare rules and pricing lookup by flight and cabin class

Key package areas:
- controller/FareController
- controller/BaggagePolicyController
- controller/FareRulesController
- service/FareService and Impl
- service/BaggagePolicyService and Impl
- service/FareRulesService and Impl
- model/Fare
- model/BaggagePolicy
- model/FareRules

Default configuration:

- port: 5006
- database: airline_pricing_db

Main endpoints:

- POST /api/fares
- GET /api/fares
- GET /api/fares/{id}
- GET /api/fares/flight/{flightId}/cabin-class/{cabinClassId}
- POST /api/fares/search
- PUT /api/fares/{id}
- DELETE /api/fares/{id}

### 6.6 seat-service

Purpose:
- cabin class configuration
- seat layout definitions and bookability flags

Key package areas:
- model/CabinClass
- service/CabinClassService
- controller (if added later)

Default configuration:

- port: 5007
- database: airline_seating_db

This service is partially initialized and will be expanded as seat inventory and booking logic are implemented.

## 7. Database Design Pattern

Each service is configured to use its own MySQL database. This keeps data ownership clear and avoids tight coupling between business domains.

Current configured service databases:

- user-service -> airline_user_service_db
- airline-core-service -> airline_core_airline_db
- location-service -> airline_location_db
- flight-ops-service -> airline_flight_db
- pricing-service -> airline_pricing_db
- seat-service -> airline_seating_db

This is the recommended pattern for a microservice design because it allows each service to evolve independently without cross-service schema locking.

## 8. Common Technologies

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- MySQL
- Maven multi-module build
- Lombok
- JWT for authentication
- REST APIs
- DTO-based communication using shared payload classes

## 9. How to Run the Project

### Prerequisites

- Java 21 installed
- Maven installed
- MySQL running locally
- create databases matching the application.yaml files

### Build all modules

```bash
mvn clean install
```

### Run a service

Example for user-service:

```bash
cd services/user-service
mvn spring-boot:run
```

You can run other services similarly:

```bash
cd services/airline-core-service
mvn spring-boot:run
```

```bash
cd services/location-service
mvn spring-boot:run
```

```bash
cd services/flight-ops-service
mvn spring-boot:run
```

```bash
cd services/pricing-service
mvn spring-boot:run
```

```bash
cd services/seat-service
mvn spring-boot:run
```

## 10. Notes for Future Expansion

This project is designed to continue growing with additional services such as:

- booking-service
- payment-service
- notification-service
- ancillary-service
- subscription-service
- api-gateway
- config-server
- service-registry

When a new service is completed, add:

- its purpose
- controller routes
- model and repository responsibilities
- database name and port
- how it integrates with the rest of the platform

## 11. Current Status

Completed/currently active service modules:

- user-service
- airline-core-service
- location-service
- flight-ops-service
- pricing-service
- seat-service

Infrastructure modules planned for later:

- service-registry
- config-server
- api-gateway

This README should be updated after each major module completion.
