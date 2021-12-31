
Стек Spring Boot(data, web, security), lombok, jackson, springdoc-openapi-ui, liquibase, postgresql;
Переменные окружения, для развертывания приложения JDBC_DATABASE_URL, JDBC_DATABASE_USERNAME, JDBC_DATABASE_PASSWORD - ориентировано на Postgres;

Для тестов API - доступно по ссылке https://new-survey-service.herokuapp.com


/** Анонимный пользователь **/

------ПРОХОЖДЕНИЕ ОПРОСА--------

тип запроса POST
path /anonymous/survey/take
header Content-Type application/json

Request Body (образец)

[
{
"user": {
"appUserId": 2
},
"survey": {
"survey_id": 1
},
"userAnswerList": [
{
"questionText": "Text of question333",
"answer": "Text answer333"
},
{
"questionText": "Text of question444",
"answer": "SINGLE CHOICE answer"
},
{
"questionText": "Text of question555",
"answer": "MULTIPLE CHOICE answer 1, MULTIPLE CHOICE answer 2"
}
]
}
]

/** Авторизованный пользователь **/

------получение списка активных опросов--------

тип запроса GET
path /user/survey/active
header Authorization Basic dXNlcjp1c2Vy - тустовый пользователь user:user

Response Body (образец)

[
{
"survey_id": 1,
"title": "Title",
"startDate": "2021-12-28T08:22:26.389Z",
"endDate": "2021-12-28T08:22:26.389Z",
"description": "Description",
"active": true
}
]

------получение пройденных пользователем опросов с детализацией по ответам--------

тип запроса GET
path /user/survey/active
header Authorization Basic dXNlcjp1c2Vy - тустовый пользователь user:user

Response Body (образец)

[
{
"completedSurveyId": 1,
"survey": {
"survey_id": 1,
"title": "Title",
"startDate": "2021-12-28T08:22:26.389Z",
"endDate": "2021-12-28T08:22:26.389Z",
"description": "Description",
"active": true
},
"userAnswerList": [
{
"questionText": "Text of question",
"answer": "Text answer",
"userAnswerId": 1
},
{
"questionText": "Text of question",
"answer": "SINGLE CHOICEanswer",
"userAnswerId": 2
},
{
"questionText": "Text of question",
"answer": "MULTIPLE CHOICE answer 1, MULTIPLE CHOICE answer 2",
"userAnswerId": 3
}
]
},
{
"completedSurveyId": 2,
"survey": {
"survey_id": 1,
"title": "Title",
"startDate": "2021-12-31T08:22:26.389Z",
"endDate": "2021-12-31T08:22:26.389Z",
"description": "Description",
"active": true
},
"userAnswerList": [
{
"questionText": "Text of question333",
"answer": "Text answer333",
"userAnswerId": 4
},
{
"questionText": "Text of question444",
"answer": "SINGLE CHOICE answer",
"userAnswerId": 5
},
{
"questionText": "Text of question555",
"answer": "MULTIPLE CHOICE answer 1, MULTIPLE CHOICE answer 2",
"userAnswerId": 6
}
]
}
]


/** Администратор **/

------добавление опроса--------

тип запроса POST
path /admin/survey/add
header Content-Type application/json
header Authorization Basic YWRtaW46YWRtaW4= - тустовый пользователь admin:admin

Request Body (образец)

{
"title": "Title2",
"startDate": "2021-12-31T08:07:26.389Z",
"endDate": "2021-12-31T08:09:26.389Z",
"description": "Description2",
"active": true
}


------изменение опроса--------

тип запроса PUT
path /admin/survey/update
header Content-Type application/json
header Authorization Basic YWRtaW46YWRtaW4= - тустовый пользователь admin:admin

Request Body (образец)

{
"survey_id": 1,
"title": "Title2",
"startDate": null,
"endDate": "2021-12-31T08:09:26.389Z",
"description": "NEW Description2",
"active": true
}



------удаление опроса--------

тип запроса DELETE
path /admin/survey/delete?id=2   - тестовый параметр идентификатора опроса
header Content-Type application/json
header Authorization Basic YWRtaW46YWRtaW4= - тустовый пользователь admin:admin


------добавление вопроса--------

тип запроса POST
path /admin/question/create
header Content-Type application/json
header Authorization Basic YWRtaW46YWRtaW4= - тустовый пользователь admin:admin

Request Body (образец)

{
"text": "Text of question",
"type": "TEXT",
"survey": {
"survey_id": 1
}
}


------изменение вопроса--------

тип запроса PUT
path /admin/survey/update
header Content-Type application/json
header Authorization Basic YWRtaW46YWRtaW4= - тустовый пользователь admin:admin

Request Body (образец)

{
"text": "Text of question",
"type": "TEXT",
"survey": {
"survey_id": 1
}
}

------удаление вопроса--------

тип запроса DELETE
path /admin/question/delete?id=2   - тестовый параметр идентификатора вопроса
header Content-Type application/json
header Authorization Basic YWRtaW46YWRtaW4= - тустовый пользователь admin:admin
