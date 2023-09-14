# M-Logistic - Parcel Management Application

## Description:
The "Warehouse" application was created to manage various aspects of warehouse operations, including customer management,
orders, deliveries and their statuses and different types of users.

## Functionalities:
- **Customer Management**: Ability to add, edit and delete customer information.
- **Order Management**: Track order status and history.
- **Delivery Management**: Monitoring delivery status, including the ability to update status and view delivery logs.
- **User Management**: Supports various user types such as drivers, employees and administrators.
- **Report generation**: Functionalities related to generating various reports, letters and shipping labels.
- **International support**: The application supports various languages, including English and Polish.

## Technologies used:
- **Spring Framework**: Including Spring Security for security management.
- **Jackson**: Support for JSON data.
- **Logback and Log4j**: Login tools.
- **MySQL**: Database.
- **JSTL**: JavaServer Pages Standard Tag Library.
- **HTML**: Partial use of Bootstrap 5 technology when creating pages.
- **CSS**: Styles for pages.
- **JavaScript**: REST support and creating dynamic functions.

## Application structure:
The application is divided into different packages such as:
- `controller`: Handles HTTP requests and manages data flow in the application.
- `service`: Contains the business logic of the application.
- `entity`: Contains entity classes that represent data models.
- `repository`: Repository interfaces for performing database operations.
- `dto`: Data Transfer Object classes for transferring data between layers.

