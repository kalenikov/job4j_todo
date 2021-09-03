create table car_brand (
    id serial primary key,
    name varchar(100)
);

create table car_model (
    id serial primary key,
    name varchar(100),
    brand_id int references car_brand
);
