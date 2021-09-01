CREATE TABLE items
(
    id          SERIAL PRIMARY KEY,
    description TEXT      NOT NULL,
    created     TIMESTAMP NOT NULL default now(),
    done        BOOLEAN            default false
);


insert into items(description)
VALUES ('item1'),
       ('item2');

select * from items