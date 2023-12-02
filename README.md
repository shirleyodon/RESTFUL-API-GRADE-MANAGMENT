# RESTFUL-API-GRADE-MANAGMENT
Academic project for grade management using the Restful Java API

## Presentation
This Maven-based project is a Java Desktop application dedicated to student grade management. It is divided into two distinct parts: a backend Restful API and a frontend Java Swing client. The backend was developed in the Eclipse IDE, while the frontend was designed in the NetBeans IDE. The backend is connected to a MySQL database and deployed on a local application server, namely Apache Tomcat.

## Features
The application provides a comprehensive set of features, including:
- Authentication
- Two privilege levels: Root & User
- Management of subjects, students, and grades: Add - Modify - Delete - Search
- Generation of grade reports
- Ranking management of students
- Generation of Pie Chart diagrams for students based on their averages
- User account management: Add - Modify - Delete - Search
- Logs Management

## Installation and Execution
To launch the project:
1. Clone this repository with `git clone`.
2. Import the `GradeManagementREST` project into the Eclipse IDE.
3. Import the `GradeManagementCLIENT` project into the NetBeans IDE.
4. Ensure that an Apache Tomcat server is operational in Eclipse.
5. Deploy the `GradeManagementREST` project on this server.
6. Launch the client by executing the file `GradeManagementCLIENT/src/main/java/eni/m1/java/ws/frame/Authentification.java`.

The necessary dependencies for each part of the project are specified in `GradeManagementCLIENT/pom.xml` and `GradeManagementREST/pom.xml`.
