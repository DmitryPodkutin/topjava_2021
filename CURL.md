## CURL COMMANDS

### AdminRestController

* GET_USER

```sh
curl -L -X GET 'http://localhost:8080/topjava/rest/admin/users/100000' 
```

* GET_USER_WITH_MEALS

```sh
curl -L -X GET 'http://localhost:8080/topjava/rest/admin/users/with-meals/100000' 
```

* GET_ALL_USERS

```sh
curl -L -X GET 'http://localhost:8080/topjava/rest/admin/users' 
```

* SAVE_USER

```sh  
curl -L -X POST 'http://localhost:8080/topjava/rest/admin/users' -H 'Content-Type: application/json' --data-raw 
'{
  "name": "NEW_USER",
  "email": "mail@mail.ru",
  "password": "password",
  "enabled": true,
  "roles": [
  "USER"
  ],
  "caloriesPerDay": 2000,
  "meals": null
  }'
```

* UPDATE_USER

```sh  
curl -L -X PUT 'http://localhost:8080/topjava/rest/admin/users/100012' -H 'Content-Type: application/json' --data-raw '{
    "name": "UPDATE_NAME",
    "email": "mail@mail.ru",
    "password": "12345",
    "enabled": true,
    "roles": [
        "USER"
    ],
    "caloriesPerDay": 2000,
    "meals": null
}'
```

* DELETE_USER

```sh  
curl -L -X DELETE 'http://localhost:8080/topjava/rest/admin/users/100012'
```

* GET_USER_BY_EMAIL

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/admin/users/by?email=admin@gmail.com'
```

### ProfileRestController

* GET_USER

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/profile'
```

* GET_USER_WITH_MEALS

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/profile/with-meals'
```

* DELETE_USER

```sh  
curl -L -X DELETE 'http://localhost:8080/topjava/rest/profile'
```

* UPDATE_USER

```sh  
curl -L -X PUT 'http://localhost:8080/topjava/rest/profile' -H 'Content-Type: application/json' --data-raw 
'{
    "id": 100000,
    "name": "UPDATE_NAME",
    "email": "mail@ya.ru",
    "password": "12345",
    "enabled": true,
    "roles": [
        "USER"
    ],
    "caloriesPerDay": 2000,
    "meals": null
}'
```

* GET_TEXT

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/profile/text'
```

### MealRestController

* GET_ALL_MEALS

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/meals'
```

* GET_MEALS_ BETWEEN

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/meals/filter?startDate=30-01-2020&startTime=10:00&endDate=30-01-2020&endTime=23:00'
```

* GET_MEAL_BY_ID

```sh  
curl -L -X GET 'http://localhost:8080/topjava/rest/meals/100007'
```

* DELETE_MEAL_BY_ID

```sh  
curl -L -X DELETE 'http://localhost:8080/topjava/rest/meals/100011'
```

* SAVE_MEAL

```sh  
curl -L -X POST 'http://localhost:8080/topjava/rest/meals' -H 'Content-Type: application/json' --data-raw 
'{
    "dateTime": "2021-01-31T13:00:00",
    "description": "Test_Meal",
    "calories": 777
}'
```

* UPDATE_MEAL

```sh  
curl -L -X PUT 'http://localhost:8080/topjava/rest/meals/100011' -H 'Content-Type: application/json' --data-raw 
'{
    "dateTime": "2021-01-31T13:10:00",
    "description": "UPDATE_MEAL",
    "calories": 777
}'
```