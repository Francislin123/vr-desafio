# Movibe Api 

- This application is responsible for the registration flow, updates among other user activities, movibe and ballads.

# Technologies

- Java 8
- Gradle 4.3.1
- Spring Boot 2.0.4.RELEASE
- Spring Security OAuth2 2.3.4.RELEASE
- Spring Fox 2.7.0
- Spring Security 5.0.7
- Hibernate Search 5.9.3.Final
- Liquibase 1.2.4
- Lombok 1.18.0
- Cloudinary Http44 1.18.0
- Cloudinary Taglib 1.18.0
- Logback Gelf 1.0.4
- Fixture Factory 3.1.0
- Hibernate Core 5.2.17.Final
- Postgresql 9.6

### To run the application just follow the steps below.

- 1 - Enter the folder where the project jar is
- 2 - Execute the following command by cmd

- java -jar movibe-api-0.0.1-SNAPSHOT.jar --spring.config.location = file: C: /Config/application-prod.yml
- OBS: This start command in the application, it finds the configurations of the configuration file of the database among other configurations (application-prod.yml)
- -Dspring.profiles.active=test

### Documentation for API (Swagger)
- Link for local access: http://localhost:8085/movibe-api/v1/swagger-ui.html#/
- Link for herokuapp access: https://movibe.herokuapp.com/movibe-api/v1/swagger-ui.html#/

### Link for test api 
- https://bitbucket.org/FrancislinReis/movibe/src/master/
- https://movibe-web.github.io/admin/auth/login

### Deploy to Heroku
- Link: https://jtemporal.com/deploy-flask-heroku/

### Configuration to generate the token
- https://academiadev.gitbook.io/joinville/seguranca/oauth2

### CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
