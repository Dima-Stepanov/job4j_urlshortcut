/*Таблица urls для хранения зарегистрированных ссылок и кодов к ним*/
create table if not exists urls
(
    id serial primary key,
    url varchar(1000) not null,
    code varchar(200) not null,
    site_id int not null references sites(id),
    total integer default 0,
    unique (url, code)
)
