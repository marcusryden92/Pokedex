drop table if exists orders_model;
drop table if exists orders;
drop table if exists model;
drop table if exists sizes;
drop table if exists brand;
drop table if exists customer;


create table customer (
                          customer_id int generated always as identity primary key,
                          name varchar(80) not null,
                          email varchar(80) not null unique
);

create table brand (
                       brand_id int generated always as identity primary key,
                       name varchar(80) not null unique
);

create table model (
                       model_id int generated always as identity primary key,
                       name varchar(80) not null unique,
                       price decimal(10,2) not null check (price > 0),
                       brand_id int not null references brand(brand_id)
);

create table sizes (
                       size int not null check (size >= 18 and size <= 50) primary key
);

create table orders (
                        order_id int generated always as identity primary key,
                        created_at timestamp not null default current_timestamp,
                        customer_id int not null references customer(customer_id)
);

create table orders_model (
                              order_id int not null references orders(order_id),
                              model_id int not null references model(model_id),
                              size INT NOT NULL REFERENCES sizes(size),
                              quantity int not null check (quantity > 0) default 1,
                              primary key (order_id, model_id, size)
);