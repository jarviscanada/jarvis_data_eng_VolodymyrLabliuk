# Introduction

In this project I developed Java application that scans directory and it's subdirectories recursively and find all
matches with Regex provided in all files. 
Application accepts three arguments : Regex, root directory, output file.
JAR file generated, dockerized and pushed to Docker Hub for further use.

# Design
Fundamental OOP principles such as Encapsulation, Inheritance, Polymorphism and Abstraction were used in this application.
Several entry points developed so that used that choose what approach to use for traversing.

# Requirements

Arguments required to run the application

1. Regex pattern
2. Root directory path
3. Output file path

# Test

Application tested within main function with usage of SLF4J logger

# Deployment

JAR file dockerized and pushed to Docker Hub

# Improvement

Several improvement should be considered to increase reliability

1. Added logic to manage situation when output directory exists within input directoy.
2. Output file located inside not existed directory (recursive creation)
3. Improved exception handling and logging for different situations.
