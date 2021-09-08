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
    engine_id int not null references engine (id),
    owner_id  int not null references persons (id)
);

create table car_drivers
(
    car_id    int not null references car (id),
    driver_id int not null references persons (id),
    constraint car_drivers_pkey
        primary key (car_id, driver_id)
);