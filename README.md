## Использованные технологии и паттерны

1) Spring Boot
2) Spring Security
3) Spring Web
3) Spring Validator
4) JPA
5) JDBC
6) PostgreSQL
7) Liquibase
8) Docker 
9) Specification
10) Model Mapper
11) Patterns: Builder, Factory, Proxy

## Steps to Setup

#### 1) Скопируйте приложение:
```
git clone https://github.com/KonovalovaAN/IT-Planeta-2024.git
```
#### 2) Перейдите в корневую папку проекта.
#### 3) Выполните команду :
```
mvnw.cmd clean package
```
#### 4) Создайте Docker image:
```
docker build ./ -t webapi
```
#### 5) Запустите приложение:
```
docker-compose up -d
```
Приложение начнет работать по адресу http://localhost:8080


## API Declarations

### 1) Аутентификация пользователя

#### API 1: Регистрация нового аккаунта

```
POST - /registration

- request
Body {
    "firstName": "string", // Имя пользователя
    "lastName": "string", // Фамилия пользователя
    "email": "string", // Адрес электронной почты
    "password": "string" // Пароль от аккаунта пользователя
}

- response
Body {
    "id": "int", // Идентификатор аккаунта пользователя
    "firstName": "string", // Имя пользователя
    "lastName": "string", // Фамилия пользователя
    "email": "string", // Адрес электронной почты
    "role": "string", // Роль аккаунта пользователя, по умолчанию при регистрации "USER"
}
```

|                                                                                                                                    Условие                                                                                                                                    | Статус |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:| :----: |
|                                                                                                                            Запрос успешно выполнен                                                                                                                            |  201   |
|firstName = null,<br>firstName = "" или состоит из пробелов,<br>lastName = null,<br>lastName = "" или состоит из пробелов,<br>email = null,<br>email = "" или состоит из пробелов,<br>email аккаунта не валидный,<br>password = null,<br>password = "" или состоит из пробелов |  400   |
|                                                                                                                      Запрос от авторизованного аккаунта                                                                                                                       |  403   |
|                                                                                                                     Аккаунт с таким email уже существует                                                                                                                      |  409   |

#### API 2: Аутентификация пользователя

```
POST - /login
request
Body
{
"email": "string", // Адрес электронной почты
"password": "string" // Пароль от аккаунта пользователя
}
response
Body {
“id”: "int" // Идентификатор аккаунта пользователя
}
```

|                Условие                 | Статус |
|:--------------------------------------:|:------:|
|        Запрос успешно выполнен         |  200   |
| Запрос от авторизованного пользователя |  200   |
|       Email или password неверны       |  401   |

#### API 3: Аккаунт пользователя

```
GET - /accounts/{accountId}
{accountId}: "int"	// Идентификатор аккаунта пользователя

- request
Body {
  empty
}

- response
Body {
    "id": "int", // Идентификатор аккаунта пользователя
    "firstName": "string", // Имя пользователя
    "lastName": "string", // Фамилия пользователя
    "email": "string" // Адрес электронной почты
}
```

|                Условие                | Статус |
|:-------------------------------------:|:------:|
|        Запрос успешно выполнен        |  200   |
|  accountId = null,<br>accountId <= 0  |  400   |
|    Неверные авторизационные данные    |  404   |
| Аккаунт с таким accountId не найден   |  403   |

#### API 4: Поиск аккаунтов пользователей по параметрам

```
GET - /accounts/search
  ?firstName={firstName}
  &lastName={lastName}
  &email={email}
  &from={from}
  &size={size}
{firstName}: "string",	// Имя пользователя, может использоваться только часть имени без учета регистра, если null - не учитывается
{lastName}: "string",	// Фамилия пользователя, может использоваться только часть фамилии без учета регистра, если null - не учитывается
{email}: "string",	// Адрес электронной почты, может использоваться только часть адреса электронной почты без учета регистра, если null - не учитывается
{from}: "int"		// Количество элементов, которое необходимо пропустить для формирования страницы с результатами (по умолчанию 0)
{size}: "int"		// Количество элементов на странице (по умолчанию 10)

- request
Body {
  empty
}

- response
Body [
      {
        "id”: "int", // Идентификатор аккаунта пользователя
        "firstName": "string", // Имя пользователя
        "lastName": "string", // Фамилия пользователя
        "email": "string" // Адрес электронной почты
      }
]

Результаты поиска сортируются по id аккаунта от наименьшего к наибольшему
```

|             Условие              | Статус |
|:--------------------------------:| :----: |
|     Запрос успешно выполнен      |  200   |
|      from < 0,<br>size <= 0      |  400   |
| Неверные авторизационные данные  |  401   |

#### API 5: Обновление данных аккаунта пользователя

```
PUT - /accounts/{accountId}
{accountId}: "int"	// Идентификатор аккаунта пользователя

- request
Body {
    "firstName": "string", // Новое имя пользователя
    "lastName": "string", // Новая фамилия пользователя
    "email": "string", // Новый адрес электронной почты
    "password": "string" // Новый пароль от аккаунта
}

- response
Body {
    "id": "int", // Идентификатор аккаунта пользователя
    "firstName": "string", // Новое имя пользователя
    "lastName": "string", // Новая фамилия пользователя
    "email": "string", // Новый адрес электронной почты
}
```

|                                                                                                                                                        Условие                                                                                                                                                         | Статус |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:------:|
|                                                                                                                                                Запрос успешно выполнен                                                                                                                                                 |  200   |
| accountId = null,<br>accountId <= 0,<br>firstName = null,<br>firstName = "" или состоит из пробелов,<br>lastName = null,<br>lastName = "" или состоит из пробелов,<br>email = null,<br>email = "" или состоит из пробелов,<br>email аккаунта не валидный,<br>password = null,<br>password = "" или состоит из пробелов |  400   |
|                                                                                                                       Запрос от неавторизованного аккаунта,<br> Неверные авторизационные данные                                                                                                                        |  401   |
|                                                                                                                                  Обновление не своего аккаунта,<br>Аккаунт не найден                                                                                                                                   |  403   |
|                                                                                                                                          Аккаунт с таким email уже существует                                                                                                                                          |  409   |

#### API 5: Удаление аккаунта пользователя

```
DELETE - /accounts/{accountId}
{accountId}: "int"	// Идентификатор аккаунта пользователя

- request
Body {
  empty
}

- response
Body {
  empty
}
```

|                                  Условие                                   | Статус |
|:--------------------------------------------------------------------------:| :----: |
|                          Запрос успешно выполнен                           |  200   |
|                    accountId = null,<br>accountId <= 0                     |  400   |
| Запрос от неавторизованного аккаунта, <br> Неверные авторизационные данные |  401   |
|   Аккаунт с таким accountId не найден, <br> Удаление не своего аккаунта    |  403   |
