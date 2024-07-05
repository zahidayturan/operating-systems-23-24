# Airline Reservation System

## Project Overview

This project involves creating a multithreaded Java program using Spring Boot and Maven. The program consists of multiple reader and writer threads accessing a shared reservation record. The application supports both synchronous and asynchronous access mechanisms.

## Links
 
  - [YouTube Link](https://youtu.be/uflVxueP7PI)

## Features
Spring Boot Web Interface: A user-friendly web interface for managing reservations.

Console-Based Sync and Async Operations:
- Synchronous Operations: Ensures data consistency using Java thread synchronization with Locks.
- Asynchronous Operations: Demonstrates potential data inconsistencies due to concurrent access.

## Components
1. Writer Threads
- makeReservation: Creates a new reservation.
- cancelReservation: Cancels an existing reservation.
2. Reader Threads:
- queryReservation: Queries the current state of a reservation.


## Implementation Steps

1. Asynchronous Access:
- Initially, the program allows reader and writer threads to access the reservation record asynchronously.
This can lead to data inconsistencies, as multiple threads may modify the record simultaneously.

2. Synchronous Access with Locks:
- To ensure data consistency, Java thread synchronization is implemented using Locks.
Multiple reader threads can access the shared data simultaneously, provided no writer thread is active.
Writer threads gain exclusive access, preventing readers from accessing the data during write operations.


## Addressing Concurrency Issues
Reader-Writer Problem:
- If multiple readers are accessing the shared data, writer threads must wait.
- Prioritizing writers can prevent writer starvation, ensuring they are not perpetually delayed by readers.
- Balancing reader and writer access to prevent either from being unfairly prioritized.

## Conclusion

This project demonstrates handling multithreaded access to shared resources in Java, ensuring data integrity through proper synchronization techniques. The solution highlights both asynchronous and synchronous access patterns, showcasing potential issues and their resolutions.
<div align="center">
<img style="border-radius: 12px" src="gif.gif" width=95% >
</div>


### Installation

```sh
$ TODO
localhost:8080
```