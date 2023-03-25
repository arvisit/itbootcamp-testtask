# ITbootcamp Java Developer Test Task
This repository contains ITbootcamp Java Developer Test Task solution.

## Requirements
**Mandatory:**
+ Multi-module Maven project (3-level three-module development architecture)
+ Version control system - Git (Public repository on GitHub)
+ Technologies:
    1. Java 11
    2. Log4j2
    3. Spring Boot 2 (or Tomcat 9 + Spring 5)
    4. Hibernate 5
    5. MySQL 8

**Optional:**
+ Docker + Docker Compose for project containerization
+ Unit and Integration tests
+ Liquibase/Flyway for database migration

**API requirements:**
+ Format: JSON
+ Architecture: REST

**Add user Endpoint:**

Main fields:
+ Lastname (40 characters, latin letters only)
+ Firstname (20 characters, latin letters only)
+ Secondname (40 characters, latin letters only)
+ Email (50 characters, default template)
+ Role (Administrator, Sale User, Customer User, Secure API User)

**Get all users Endpoint:**

Main fields:
+ Fullname
+ Email
+ Role

Records should be sorted ascending by email

**Optional:**
+ Error handling
+ Pagination for users (10 records per page)
