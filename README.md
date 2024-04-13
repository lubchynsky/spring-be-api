# spring-be-api
This application will be used in created test frameworks

# Swagger api
http://localhost:8080/swagger-ui/index.html

# Docker standalone image
- gradle clean
- gradle build
- docker build -t my-be-api .
- docker run -p 8080:8080 my-be-api

# Docker compose (be-api+DB)
- gradle clean
- gradle build
- docker build -t my-be-api .
- docker-compose up

# To use mocked repository
- -Dspring.profiles.active=test