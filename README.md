# Student Management System (SMS)

## What It Does
A simple website for students and professors. Students can sign up, log in, and update their info. Professors can sign up, log in, and remove students.

## Features
- Students: Sign up, log in, update details.
- Professors: Sign up, log in, delete students.
- Safe passwords using BCrypt.
- Clean, dark design.

## Tools Used
- Java 11
- Tomcat 9 (web server)
- MySQL 8.0 (database)
- JSP and Servlets (web pages)
- Maven (build tool)
- Git (for code tracking)

## How to Set Up
1. Get this code: https://github.com/TheArunsPlanet/Student-Management-System
2. Install Java 11, Maven, and MySQL 8.0.
3. Make the `sms_db` database in MySQL:
```sql
CREATE DATABASE sms_db;
USE sms_db;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(60),
    name VARCHAR(100)
);
CREATE TABLE professors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(60),
    name VARCHAR(100)
);
```
