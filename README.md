# Тестовое задание ToDo list

### Подготовка окружения

При необходимости переопределить следующие переменные окружения:
* POSTGRES_HOST - по умолчанию `localhost`
* POSTGRES_PORT - по умолчанию `5432`
* POSTGRES_DB_NAME - по умолчанию `todo`
* POSTGRES_USERNAME - по умолчанию `postgres`
* POSTGRES_PASSWORD - по умолчанию `123`
* POSTGRES_SCHEMA - по умолчани. `public`
* TOKEN_KEY - ключ верификации jwt (есть значение по умолчанию)

### Запуск проекта
Проект запускается командой
```
.\gradlew.bat bootRun
```
или 
```
./gradlew bootRun
```

### Примеры запросов

Регистрация
```http request
### Expect { "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjIsImVtYWlsIjoidGVzdEBtYWlsLmNvbSIsInN1YiI6InRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MjU5MDk3MDIsImV4cCI6MTcyNjA1MzcwMn0.uy-96tydibKwQ8JIXU06T0felK2kai88ZzTPllMpt9s"}
POST http://localhost:8080/api/v1/auth/sign-up
Content-Type: application/json

{
    "email": "test@mail.com",
    "password": "123test"
}
```

Аутенфикация
```http request
### Expect { "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjIsImVtYWlsIjoidGVzdEBtYWlsLmNvbSIsInN1YiI6InRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MjU5MDk3MDIsImV4cCI6MTcyNjA1MzcwMn0.uy-96tydibKwQ8JIXU06T0felK2kai88ZzTPllMpt9s"}
POST http://localhost:8080/api/v1/auth/sign-in
Content-Type: application/json

{
    "email": "test@mail.com",
    "password": "123test"
}
```

Получение событий пользователя
```http request
### Expect [ { "id": 4, "header": "test", "description": "simple description", "createdAt": "2024-09-10T00:24:23.657462", "state": "READY" } ]
GET http://localhost:8080/api/v1/todo
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjIsImVtYWlsIjoidGVzdEBtYWlsLmNvbSIsInN1YiI6InRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MjU5MDk3MDIsImV4cCI6MTcyNjA1MzcwMn0.uy-96tydibKwQ8JIXU06T0felK2kai88ZzTPllMpt9s
```

Добавление события для пользователя
```http request
### Expect [ { "id": 4, "header": "test", "description": "simple description", "createdAt": "2024-09-10T00:24:23.657462", "state": "READY" } ]
POST http://localhost:8080/api/v1/todo
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjIsImVtYWlsIjoidGVzdEBtYWlsLmNvbSIsInN1YiI6InRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MjU5MDk3MDIsImV4cCI6MTcyNjA1MzcwMn0.uy-96tydibKwQ8JIXU06T0felK2kai88ZzTPllMpt9s

{
    "header": "test",
    "description": "simple description",
    "state": "READY"
}
```

Обновление события для пользователя
```http request
### Expect [ { "id": 4, "header": "test", "description": "simple description", "createdAt": "2024-09-10T00:24:23.657462", "state": "READY" } ]
PUT http://localhost:8080/api/v1/todo
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjIsImVtYWlsIjoidGVzdEBtYWlsLmNvbSIsInN1YiI6InRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MjU5MDk3MDIsImV4cCI6MTcyNjA1MzcwMn0.uy-96tydibKwQ8JIXU06T0felK2kai88ZzTPllMpt9s

{
    "header": "test",
    "description": "simple description",
    "state": "READY"
}
```

Удаление событий пользователя
```http request
### Expect code 200
DELETE http://localhost:8080/api/v1/todo
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjIsImVtYWlsIjoidGVzdEBtYWlsLmNvbSIsInN1YiI6InRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MjU5MDk3MDIsImV4cCI6MTcyNjA1MzcwMn0.uy-96tydibKwQ8JIXU06T0felK2kai88ZzTPllMpt9s

{
    "ids": [
        1, 2, 3
    ]
}
```