# VR CHALLENGE API 

- This application is responsible for controlling the entire flow of movement of the benefit card.

# Technologies

- Java 11
- Spring Boot 2.7.5
- Spring Fox 3.0.0
- Lombok 1.18.24
- Hibernate Core 5.2.17.Final
- H2 Data Base

### To run the application just follow the steps below.

- 1 - Enter the folder where the project jar is
- 2 - Execute the following command by cmd

- java -jar vrDesafio-0.0.1-SNAPSHOT.jar --spring.config.location = file: C: /Config/application-prod.yml
- OBS: This start command in the application, it finds the configurations of the configuration file of the database
- among other configurations (application-prod.yml)
- -Dspring.profiles.active=test

### Documentation for API (Swagger)
- Link for local access: http://localhost:8080/vr/swagger-ui/index.html

### Link for Git API 
- https://github.com/Francislin123/vr-desafio.git

### H2
- Link: http://localhost:8080/vr/h2/login.jsp?jsessionid=c4f0dcc4975b872b21d3d9950c1719f5