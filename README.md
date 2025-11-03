# Account Management System

## Overview
This project implements Account Management System built with Java 21, Spring Boot 3.5.7, and PostgreSQL.

## Tech Stack
- Java 21
- Spring Boot 3.5.7
- PostgreSQL
- Docker & Docker Compose
- Maven
- Swagger / OpenAPI

## Features

- Create and fetch Accounts

- Create Transactions

- Validations for duplicate document numbers and invalid data

- Automatic creation of tables on startup

- Pre-population of the operation_types table on application startup  

- OpenAPI 3.0 documentation via Swagger UI  

- Fully containerized setup using Docker  

## Build the project

- mvn clean install

## Starting the application using Docker Compose

- docker-compose up -d --build

- The latest JAR is automatically copied to the app container.

- This will start both the Spring Boot app and PostgreSQL container.

## Access the application

- Swagger UI: http://localhost:8080/swagger-ui.html

## Endpoints Overview

## Create Account

- POST /accounts
- Request:
```json
{
  "document_number": "12345678900"
}
```
Response:
```json
{
  "account_id": 1,
  "document_number": "12345678900"
}
```
## Get Account by ID

- GET /accounts/{accountId}

Response:
```json
{
  "account_id": 1,
  "document_number": "12345678900"
}
```

## Create Transaction

- POST /transactions
- Request:
```json
{
  "account_id": 1,
  "operation_type_id": 4,
  "amount": 123.45
}
```
Response:
```json
{
  "transaction_id": 1,
  "account_id": 1,
  "operation_type_id": 4,
  "amount": 123.45
}
```

