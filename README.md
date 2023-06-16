[![codecov](https://codecov.io/gh/Dima-Stepanov/job4j_url_shortcut/branch/master/graph/badge.svg?token=Qo0v6uulOf)](https://codecov.io/gh/Dima-Stepanov/job4j_url_shortcut)
[![Java CI with Maven](https://github.com/Dima-Stepanov/job4j_url_shortcut/actions/workflows/maven.yml/badge.svg)](https://github.com/Dima-Stepanov/job4j_url_shortcut/actions/workflows/maven.yml)

# Сервис - UrlShortCut

## Описание проекта
Чтобы обеспечить безопасность пользователей, все ссылки на сайте заменяются ссылками на наш сервис.
Пользователь регистрирует свой сайт после чего получает логин и пароль.
После авторизации пользователь регистрирует ссылки на своем сайте и в ответ получает сокращенные ссылки в виде кода.
Не зарегистрированный пользователь при вводе кодированной ссылки приноровляется на привязанную для этого кода страницу.
Так же сервис регистрирует статистику для каждого сайта, где фиксируется количество обращения по каждой ссылки.

## Стек технологий
Java 17 <br>
PostgreSQL 14 <br>
Spring BOOT 2.7.12 <br>
Spring data jpa <br>
Spring security <br>
Spring test <br>
AsserJ <br>
Junit <br>
Liqubase 4.20 <br>
Mockito <br>
Lombok 1.18 <br>

## Требование к окружению
JDK 17, Maven 3.8, PostgreSQL 14, Postman <br>

## Запуск приложения

### Для запуска проекта docker-compose:
<b> Описанные ниже действия актуальны для ОС Linux Ubuntu/Mint </b><br>
1. Установите <b>docker</b> и <b>docker-compose</b>.<br>
2. Создайте каталог <b>projects</b>. Команда <b>mkdir projects</b>. Перейдите в каталог <b>projects</b>. Команда <b>cd projects</b>.<br>
3. Клонируйте репозиторий <b>job4j_url_shortcut</b>. Команда <b>git clone</b> https://github.com/Dima-Stepanov/job4j_url_shortcut.<br>
4. Перейдите в каталог <b>job4j_url_shortcut</b>. Команда <b>cd job4j_url_shortcut</b>. <br>
5. Собираем приложения. Выполняем команду <b>docker-compose build</b>.<br>
6. Запускаем приложения в фоновом режиме <b>docker-compose up -d</b>.<br>
7. Для остановки приложение выполните команду <b>docker-compose down</b>.<br>

### Для запуска K8s
1. Клонируем проект, и заходим в каталог K8s </br>
2. В командной строке выполняем команду <b> bash ./commands_start.sh </b>
3. Для остановки пакетов <b> bash ./commands_destroy.sh </b>

### Запуск приложения локально
1. Создайте базу данных url_short_cut_db при помощи консоли PostgreSQL или терминала pgAdmin:<br>
   """CREATE DATABASE url_short_cut_db"""
2. Скопировать проект из репозитория по ссылке:[Сервис - UrlShortCut](git@github.com:Dima-Stepanov/job4j_url_shortcut.git)
3. Перейдите в корень проекта и при помощи Maven соберите проект командой:<br>
   """mvn install -Dmaven.test.skip=true"""
4. После успешной сборки проекта перейдите в каталог собранного проекта <b>target</b> и запустите приложение
   командой:<br>
   """java -jar job4j_url_shortcut-0.0.1-SNAPSHOT.jar"""
5. В Postman перейдите по ссылке http://localhost:8080/ <br>
    и действуйте по рекомендациям **Взаимодействие с приложением**. 

## Взаимодействие с приложением

### Общая схема модэлей приложения

![](img/sheme_dependse.jpg) <br>
Рисунок 1. Общая схема моделей данных <br>

### 1. Регистрация сайта.
Сервисом могут пользоваться разные сайты.<br>
Каждому сайту выдается пару пароль и логин.<br>
Чтобы зарегистрировать сайт в систему нужно отправить запроса.<br>
URL <br>
POST /registration <br>
C телом JSON объекта. <br>
{site : "job4j.ru"} <br>

![](img/POST_REGISTRATION.jpg) <br>
Рисунок 2 запрос на регистрацию.<br>

### Ответ от сервера.
{registration : true/false, login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД} <br>
Флаг registration указывает, что регистрация выполнена или нет, то есть сайт уже есть в системе. <br>

![](img/RECUEST_REGISTRATION.jpg) <br>
Рисунок 3 ответ от сервера при удачной регистрации.<br>

### 2. Авторизация.
Авторизацию реализована через JWT.<br>
Пользователь отправляет POST запрос с login и password и получает ключ.<br>
Этот ключ отправляет в запросе в блоке HEAD.<br>
Без авторизации доступны запросы POST /registration, GET /redirect/УНИКАЛЬНЫЙ_КОД.<br>

![](img/POST_LOGIN.jpg) <br>
Рисунок 4 Запрос/ответ на авторизацию пользователя. <br>

### 3. Регистрация URL.
Поле того, как пользователь зарегистрировал свой сайт он может отправлять на сайт ссылки и получать преобразованные
ссылки.  <br>
Запрос  <br>
POST /convert  <br>
C телом JSON объекта.  <br>
{url: "https://job4j.ru/profile/exercise/106/task-view/532"}  <br>
Ответ с телом JSON объекта.  <br>
{"code":"2C4Q4pq6"}  <br>

![](img/POST_CONVERT.jpg) <br>
Рисунок 5. Запрос/ответ на регистрацию защищенной ссылки.<br>

### 4. Переадресация. Выполняется без авторизации.
Когда сайт отправляет ссылку с кодом в ответ вернутся ассоциированный адрес и статус 302.<br>
Запрос <br>
GET /redirect/УНИКАЛЬНЫЙ_КОД <br>
Ответ от сервера в заголовке. <br>
HTTP CODE - 302 REDIRECT URL <br>

![](img/GET_REDIRECT.jpg) <br>
Рисунок 6. Запрос ответ на переадресацию. <br>

### 5. Статистика.
В сервисе считается количество вызовов каждого адреса. <br>
По сайту можно получить статистку всех адресов и количество вызовов этого адреса. <br>
Статистика выходит только по адресам которые пренадлежат авторизованному пользователю. <br>
Запрос <br>
GET /statistic <br>
Ответ от сервера JSON. <br>
{ <br>
{url : URL, total : 0}, <br>
{url : "https://job4j.ru/profile/exercise/106/task-view/532", total : 103} <br>
} <br>

![](img/GET_STATISTIC.jpg) <br>
Рисунок 7. Запрос ответ статистики по ссылкам. <br>

>Так же в приложении реализована валидация входных параметров
для генерации паролей и кодов для ссылок реализован класс CodeGenerate


### Контакты

> email: [haoos@inbox.ru](mailto:haoos@inbox.ru) <br>
> tl: [Dima_software](https://t.me/Dima_software) <br>
> github.com: [Dima-Stepanov](https://github.com/Dima-Stepanov)
