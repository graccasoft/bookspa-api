# BookSpa Backend API
BookSpa Reservation Backend

https://bookspa.app

## Technologies
* Spring Boot 3.0.6
* MySQL

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Gradle 7.5.1


To build and run the project, follow these steps:

* Clone the repository
* Navigate to the project directory: `cd red-kokia-back`
* Create a MySQL DB
* Create environment variables:
    * `export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/my_db`
    * `export SPRING_DATASOURCE_USERNAME=mydbuser`
    * `SPRING_DATASOURCE_PASSWORD=mydbpassword`
    * These have to match your MySQL db settings

* Build the project: `gradle build`
* Run the project: `gradle bootRun`

-> The application will be available at http://localhost:8080.
