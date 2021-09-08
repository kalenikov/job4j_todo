create table persons
(
    id   serial primary key,
    name varchar(100)
);

create table engine
(
    id   serial primary key,
    name varchar(100)
);

create table car
(
    id        serial primary key,
    engine_id int not null unique references engine (id),
    owner_id  int not null unique references persons (id)
);

create table car_drivers
(
    id        serial primary key,
    car_id    int not null unique references car (id),
    driver_id int not null unique references persons (id)
);