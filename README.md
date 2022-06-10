# BACKEND CODING CHALLENGE

This project is Java Spring Boot Framework project what is designed REST API endpoints that handle Authentication and CRUD operations for generated time series weather data.

- User credential (username and password) submission endpoint that is exposed at /login (POST).
- User create endpoint is exposed at /user (POST).
- User information endpoint is exposed at /user/{user_id} (GET).
- Updating user information endpoint is exposed at /user/{user_id} (PUT/POST).
- Deleting the user endpoint is exposed at /user/{user_id} (DELETE).
- Weather information endpoint is exposed at /weather (GET) and can be filtered by given parameters (condition, time range, location, average etc.).
- All endpoints will accept JSON and/or return JSON responses.

> Note: Versions should be examined in the pom.xml file. There are dependencies versions that we use, such as Spring Boot Framework version, Java version. Application properties include database connections.
