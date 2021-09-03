CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE
);

insert into users(name, email)
VALUES ('user1', 'email1'),
       ('user2', 'email2');

alter table items
    add user_id int;

alter table items
    add constraint items_users_id_fk
        foreign key (user_id) references users;