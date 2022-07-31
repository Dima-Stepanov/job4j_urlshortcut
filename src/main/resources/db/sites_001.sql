/*Таблица sites содержит список зарегистрированных сайтов*/
create table if not exists sites
(
    id           serial primary key,
    login        varchar(200) not null,
    password     varchar(200) not null,
    registration boolean default true,
    unique (login)
)