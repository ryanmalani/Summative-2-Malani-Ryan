# Summative Assessment: Relational Data

## Challenge: Relational Data

This project involves the creation of a Java DAO for an existing database structure. In addition, you will produce a 1-3 minute video summary of the project.

## Structure

Your solution must have the following structural elements:

- Your solution must be in an IntelliJ project called `Summative-2-Lastname-Firstname` where Firstname and Lastname are your first and last name respectively.

- Your solution must use JdbcTemplates and prepared statements.

- Your solution must contain a full set of unit/integration tests.

## Methodology

- You must utilize TDD and Red Green Refactor when developing this project.

- You must use Pivotal Tracker to track your tasks for this project.

## Requirements/Features

This project is a DAO and relational database that keeps track of books in a bookstore.

- Your DAO API must allow callers to create, read, read all, update, and delete Books, Authors, and Publishers in the system. The system must also allow callers to find Books by Author.

- Your solution must be based on the database created by the following SQL script:

   ```sql
    create schema if not exists book_store;
    use book_store;

    create table if not exists book (
      book_id int not null auto_increment primary key,
        isbn varchar (15) not null,
        publish_date date not null,
        author_id int not null,
        title varchar (70) not null,
        publisher_id int not null,
        price decimal(5,2) not null
    );

    create table if not exists author (
      author_id int not null auto_increment primary key,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        street varchar(50) not null,
        city varchar(50) not null,
        state char(2) not null,
        postal_code varchar(25) not null,
        phone varchar(15) not null,
        email varchar(60) not null
    );

    create table if not exists publisher (
      publisher_id int not null auto_increment primary key,
        name varchar(50)not null,
        street varchar(50) not null,
        city varchar(50) not null,
        state char(2) not null,
        postal_code varchar(25) not null,
        phone varchar(15) not null,
        email varchar(60) not null
    );

    /* Foreign Keys: book */
    alter table book add constraint fk_book_author foreign key (author_id) references author(author_id);
    alter table book add constraint fk_book_publisher foreign key (publisher_id) references publisher(publisher_id);
   ```

## Video Summary Requirement

- Create a 1-3 minute video summary highlighting your project. Be sure to highlight the sections of the project that are important
in meeting the project requirements. Be sure to provide the following video requirements:
    * Video should be created in MP4 format.
    * Video should include a screen capture recording to display the important sections of the project.


## Submission

Once you have completed your assessment, it’s time to submit. Follow the steps from your first summative submission, but customize the naming conventions for this particular assignment. Also, remember to submit your video summary. Make sure to double check that your submission is completed.

## Grading Requirements

In this challenge, your grade will consist of a combination of general setup and format requirements and your actual code. See the criteria below for more detailed information:

### **General set up and format requirements: 10%**

- Solution must be in an IntelliJ project called Summative-2-Lastname-Firstname.
- Project must be built using Spring Boot and Spring MVC. Initialize your project using start.spring.io.
- Project must use JDBC that follows the pattern shown in this module.
- Project comes with a full set of test cases.
- Project implements basic SQL queries to communicate with the DB.

### **Book Entity Code: 30%**

- Code is clean (follows general patterns as presented in class).
- Implementation of DAO for:
  - Create
  - Read
  - Read All
  - Update
  - Delete
- Test of DAO for:
  - Create
  - Read
  - Read All
  - Update
  - Delete
- Implementation and test of DAO for:
  - Search Book by Author

### **Author Entity Code: 30%**

- Code is clean (follows general patterns as presented in class)
- Implementation of DAO for:

- - Create
  - Read
  - Read All
  - Update
  - Delete
- Test of DAO for:
  - Create
  - Read
  - Read All
  - Update
  - Delete

### **Publisher Entity Code: 30%**

- Code is clean (follows general patterns as presented in class)
- Implementation of DAO for:
  - Create
  - Read
  - Read All
  - Update
  - Delete
- Test of DAO for:
  - Create
  - Read
  - Read All
  - Update
  - Delete


---

© 2021 Trilogy Education Services, a 2U, Inc. brand. All Rights Reserved.