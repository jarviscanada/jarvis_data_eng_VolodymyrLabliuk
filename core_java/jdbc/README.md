!# Introduction

In this project I developed Java application that reads user input perform operations based on it.
User can buy or sell shares. All data is up-to-date and retrieved from stock API. User can decide to buy or not to buy
share for current price. If user decide to sell shares they all are being sold.

# Design
Application developer using internal and external libraries. Java Jackson lib is used serialization and deserialization,
mockito for unit testing, PSQL database to store quotes and positions bought.
CRUD operations are declared in CrudDao interface and implemented by 
Quote and Position class.

# Test

Application tested using unit and integration testing techniques with usage of SLF4J logger.

# Deployment

JAR file dockerized and pushed to Docker Hub

# Improvement

Several improvement should be considered to increase reliability

1. Ability to partly sell shares
2. All vulnerable information like password and keys should be stored in ENV file
3. Data retrieved from API can be cleaned to get rig of redundant characters.
