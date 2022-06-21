# BACKEND CODING CHALLENGE

This project is Java Spring Boot Framework project what is designed REST API endpoints that handle Authentication and CRUD operations for generated time series weather data.

- User credential (username and password) submission endpoint that is exposed at /login (POST).
- User create endpoint is exposed at /user (POST).
- User information endpoint is exposed at /user/{user_id} (GET).
- Updating user information endpoint is exposed at /user/{user_id} (PUT/POST).
- Deleting the user endpoint is exposed at /user/{user_id} (DELETE).
- Weather information endpoint is exposed at /weather (GET) and can be filtered by given parameters (condition, time range, location, average etc.).
- All endpoints will accept JSON and/or return JSON responses.

_> Note: Versions should be examined in the pom.xml file. There are dependencies versions that we use, such as Spring Boot Framework version, Java version. Application properties include database connections._

## How to run this project?

Mysql server must be installed. You can connect to this server running on port 3306 (it should be default) with the root user password. It should contain a schema named test. After these operations are done, the sent jar file will work with the updated java sdk. Better to run it from terminal as the outputs are not in the log file. It is run with the command line 
```sh
java -jar Weather-rest-api.jar
```
 in the path of the jar file.

## Without token save user

![withOuthTokenSaveUser](https://user-images.githubusercontent.com/60318526/173068900-79342c75-1f9d-4545-a0b6-c08c5d7c92e5.PNG)

## Login

![Login](https://user-images.githubusercontent.com/60318526/173067177-a68866d9-12df-4059-a88b-95a8c5d8f9bb.PNG)

## With token save user

![withTokenAuthSaveUser](https://user-images.githubusercontent.com/60318526/173069111-9b6c278d-c8db-426b-b219-209c98b9b5a8.PNG)

## Login new user

![LoginNewUser](https://user-images.githubusercontent.com/60318526/173067284-672ff8bd-b8a2-4a65-9fd2-34fa2426941d.PNG)

## With token no authorization save user

![withTokenNoAuthSaveUser](https://user-images.githubusercontent.com/60318526/173069249-390a9f91-aa58-428d-983d-072278a68e80.PNG)

## Update user

![updateUser](https://user-images.githubusercontent.com/60318526/173067334-2038cddb-2813-495f-a992-33458ea94115.PNG)

## Get all user

![getAllUsers](https://user-images.githubusercontent.com/60318526/173067014-e98f57c1-b6d6-4a4e-97cf-afe9ccfbeaf8.PNG)

## Get user

![getUser](https://user-images.githubusercontent.com/60318526/173067081-518ce103-a464-47ba-b373-5d17f615f4a3.PNG)

## Database before delete user

![databaseUpdateBefore](https://user-images.githubusercontent.com/60318526/173070065-14059e79-914f-4667-9558-480c7240a37f.PNG)

## Delete user

![deleteUser](https://user-images.githubusercontent.com/60318526/173069374-cabc9c44-9573-4486-bbae-9d3931282e37.PNG)

## Database after delete user

![databaseAfterDelete](https://user-images.githubusercontent.com/60318526/173070004-c277f83d-2114-47e6-89a5-f24ad24e789d.PNG)

## Empty weather table

![emptyWeatherTable](https://user-images.githubusercontent.com/60318526/173070446-6ece93cc-1cfd-4646-94a7-0c604df00d38.PNG)

## Add random weather with size

![bulkaddRandomWeathersWithSize](https://user-images.githubusercontent.com/60318526/173070566-a567addf-2565-4576-bfe5-b4ddb1323154.PNG)

## Weather table after add

![weathersAfterBulk](https://user-images.githubusercontent.com/60318526/173070785-8277fb18-111e-4567-ac73-e16ecd7d909d.PNG)

## Weather table before update

![weatherTableBerforeUpdate](https://user-images.githubusercontent.com/60318526/173070892-e3e11062-e98f-4836-91ff-fdef29abd832.PNG)

## Update weather

![updateWeather](https://user-images.githubusercontent.com/60318526/173070949-d706b6ef-4606-4e98-a122-17e3c1c1a2c7.PNG)

## Weather table after update

![weatherTableAterUpdate](https://user-images.githubusercontent.com/60318526/173071024-a166f08c-cd94-4b82-9fe0-5b11224ec947.PNG)

## Delete weather

![deleteWeather](https://user-images.githubusercontent.com/60318526/173071098-ef4e934b-e51e-4dff-a10f-96f8824a56e8.PNG)

## Weather table after delete

![weatherTableAfterDelete](https://user-images.githubusercontent.com/60318526/173071294-704bbaf0-c3b4-4981-82b8-e18bc9a34208.PNG)

## Get weather

![getWeathers](https://user-images.githubusercontent.com/60318526/173071369-7de11a22-24c8-4456-bd55-4f9970b17aa1.PNG)

## Save weather

![saveWeather](https://user-images.githubusercontent.com/60318526/173071412-64b53e2a-9b27-4925-b57a-e2a724157208.PNG)

## Weather table after save

![weatherTableAfterSave](https://user-images.githubusercontent.com/60318526/173071466-05d6b278-48f0-428e-9984-ff8d98fb615e.PNG)

