<h1 align="center"> Java-Rental-System-MySQL-REST-API </h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://github.com/cagilceren/PHP-Simple-TO-DO-List-REST-API/blob/main/README.md" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="https://github.com/cagilceren/PHP-Simple-TO-DO-List-REST-API/graphs/commit-activity" target="_blank">
    <img alt="Maintenance" src="https://img.shields.io/badge/Maintained%3F-yes-green.svg" />
  </a>
</p>
<p>

 </p>

<br>

This project has been created as a part of semester project. 

In this project, I have created REST API Service for a rental system App. This rental system is consisting of three components: [User](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/User.java), [Inventory](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Inventory.java) and [Rental](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Rental.java). They are representing three different tables in SQL. 

User table has the information about the users of the system:
- name
- email
- password

In inventory table there are different objects and their properties:
- inventory no
- description
- count (number of the item)
- condition (must be between 1 and 5)
- serial no
- lendability 

Rental table represents the renting information: 
- inventory id (as foreign key)
- name
- adress (of the person who borrows the item)
- email (of the person who borrows the item)
- phone (of the person who borrows the item)
- deposit
- borrow date
- due date
- return date
- comment (optional)
- lending user
- receiving user


## Build With

- Java
- MySQL
- JPA
- Spring
- Maven

## Tools & Technologies

- Postman
- JSON
- Rest API

## Highlights

In this project I have used different modules for different compartments in the project, so that the project is easily upgradeable. 

During the project I have used Spring framework and JPA, which offer many facilities while creating Java REST API Services. That is why, I didn't have to manually deal with many things, like SQL Injection and creating JSON documents (see: [PHP Rest API](https://github.com/cagilceren/PHP-Simple-TO-DO-List-REST-API.git)). 

Thanks to the Spring framework, I created CRUD functions based on [RESTful web API design](https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design) by related annotations. These CRUD functions are

- to **Create** a new reminder to the database `post()`,
	
- to **Read** a existing reminder in the database `get()`,
	
- to **Update** an existing reminder in the database `put()`,
	
- to **Delete** an existing reminder from the database `delete()`.

Moreover, i have added some logic controls. I have checked,
- if "count" and "deposit" are positive numbers.
- if "condition" is between 1 and 5.
- if email has the right format.
- if the item is lendable, before adding some records to [Rental](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Rental.java).
- if there is an item with the given inventory id, before adding records to [Rental](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Rental.java), or updating the records in [Rental](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Rental.java) or getting data from [Rental](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Rental.java).
- if "count" is already "0" before adding records to [Rental](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/java/com/rental/app/model/Rental.java).

During the logic controls I have checked the possible error resources and threw a related Exception. Successful requests return "HTTP 200 OK" while unsuccessful ones return "HTTP 400 Bad Request" or "HTTP 404 Not Found".


Additionally, I have created a `getAllRentalByInventoryId()` function in order to get all existing rental data with the given id of inventory and which are not returned (`` returnDate = null; ``) or which are returned.

## TODO

- User authorization
- Export data in .csv format
- Import a search function


## Usage

> 1) Clone the repository to your local machine

```sh
$ git clone https://github.com/cagilceren/Java-Rental-System-MySQL.git
```

> 2) Install MySQL Workbench and MySQL Server. Import the files with the .sql extension and open the [db-schema.mwb](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/db-schema.mwb) to see the overview of the database.

> [Download MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

> 3) Install Postman and import the file "[rental.postman_collection.json](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/rental.postman_collection.json)".

> [Download Postman](https://www.postman.com/downloads/)

> 4) Open the file "[application.properties](https://github.com/cagilceren/Java-Rental-System-MySQL/blob/master/src/main/resources/application.properties)" under the folder rest-service/src/main/resources in your favorite editor to be able to check and update the code as your credential.

> 5) Go to the repository folder and run the project 

```sh
$ cd ./Java-Rental-System-MySQL
$ mvn spring-boot:run

```

## Authors

<img src="https://avatars.githubusercontent.com/u/45261915?v=2" width="25" height="25"> **Cagil Ceren Aslan**




- Github: [@cagilceren](https://github.com/cagilceren)

## Contributing

I am happy to have some improvement ideas for my project :)
