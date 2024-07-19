# Introduction

This project is a Java-based application for managing trading accounts, securities, and market data. It includes features for creating and managing traders, accounts, quotes, security orders, and positions. It allows you to buy and sell shares by making API calls to different endpoints

# Quick Start

Clone the Repository:

git clone git@github.com:jarviscanada/jarvis_data_eng_VolodymyrLabliuk.git
cd springboot
Build the Project: ./mvnw clean install
Run the Application: ./mvnw spring-boot:run

Access the API: https://api.iex.cloud/v1/data/core/

API Documentation can be accessed at https://iexcloud.io/docs

# Implemenation

## Architecture

src
L-- main
+-- java
¦   L-- ca
¦       L-- jarvis
¦           +-- account
¦           ¦   +-- Account.java
¦           ¦   L-- AccountDao.java
¦           +-- config
¦           ¦   +-- AppConfig.java
¦           ¦   L-- MarketDataConfig.java
¦           +-- iexQuote
¦           ¦   +-- IexQuote.java
¦           ¦   L-- IexQuoteJpaRepository.java
¦           +-- market
¦           ¦   +-- MarketOrder.java
¦           ¦   L-- MarketOrderDto.java
¦           +-- order
¦           ¦   +-- OrderController.java
¦           ¦   L-- OrderService.java
¦           +-- position
¦           ¦   +-- Position.java
¦           ¦   +-- PositionDao.java
¦           ¦   L-- PositionId.java
¦           +-- quote
¦           ¦   +-- Quote.java
¦           ¦   +-- QuoteController.java
¦           ¦   +-- QuoteDao.java
¦           ¦   L-- QuoteService.java
¦           +-- securityOrder
¦           ¦   +-- SecurityOrder.java
¦           ¦   L-- SecurityOrderDao.java
¦           +-- trader
¦           L-- utils
¦           L-- Application.java
L-- resources
L-- test
L-- java
+-- QuoteDao_IntTest.java
+-- QuoteService_IntTest.java
+-- QuoteService_UnitTest.java
+-- SimpleSaveTest.java
L-- TestConfig.java



## Scripts

Use Dockerfile and init.sql to create docker containers and initialize database sctructure

## Database Modeling

Trader
- id
- first_name
- last_name
- dob
- country
- email
- Account
- id
- trader_id
- amount

Quote
- ticker
- last_price
- bid_price
- bid_size
- ask_price
- ask_size

SecurityOrder
- id
- account_id
- status
- ticker
- size
- price
- notes

Position View
- account_id
- ticker
- position

# Test

Run the tests using Maven: ./mvnw test

# Deployment

Package the Application: ./mvnw package

Run the JAR: java -jar target/springboot-0.0.1-SNAPSHOT.jar

Configure Environment: Update application.properties with your database configuration and other settings.

# Improvements

- Enhance Error Handling:
- Improve error handling with custom exception classes and controllers.

- Add More Unit Tests:
- Increase test coverage for all modules and edge cases.

- Implement Security:
- Add authentication and authorization mechanisms to secure the API.

- Optimize Performance:
- Optimize database queries and improve caching mechanisms.

- Add Documentation:
- Improve API documentation using tools like Swagger.