# JavaScript and TypeScript Exercises

## Introduction
This project is a practice in coding with JavaScript and TypeScript, where I have solved different problems using JS/TS and implemented a doubly linked list. The primary focus of this project is to enhance my understanding of JS/TS codding as well as Object-Oriented Programming (OOP) principles and adhere to the DRY (Don't Repeat Yourself) principle.

## Design
The design of the doubly linked list follows core OOP principles:
- **Encapsulation**: The internal state of the list and its nodes is managed through class properties and methods.
- **Abstraction**: The complexity of list operations is hidden behind simple method calls.
- **Inheritance and Polymorphism**: While not directly applied in this implementation, the structure is designed to be extendable for future enhancements.

The doubly linked list includes the following key features:
- **Node Class**: Represents each element in the list with pointers to the previous and next nodes.
- **DoublyLinkedList Class**: Manages the list with methods to add, remove, and traverse nodes while ensuring no repetitive code (DRY principle).

## Test
Manual testing were used to ensure the correctness of the doubly linked list implementation. The tests cover:
- Adding nodes to the list.
- Removing nodes from the list.
- Traversing the list in both forward and backward directions.

## Deployment
This project is a practice implementation and does not include deployment steps for a production environment. However, the code can be run locally with Node.js. Ensure you have Node.js installed, then execute:

# Compile TypeScript to JavaScript
tsc

# Run the compiled JavaScript
node built/doubly-linked-list/DoublyLinkedList.js

## Improvement
Future improvements to this project could include:

- Implementing additional list operations such as sorting and searching.
- Enhancing the Node and DoublyLinkedList classes to support generics, allowing for type flexibility.
- Integrating with a front-end application to visualize the list operations.
- Adding performance benchmarks to analyze and optimize the list operations.
