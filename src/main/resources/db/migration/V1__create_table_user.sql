create table if not exists client
(
id bigserial primary key ,
bank_id bigint,
first_name varchar(100),
surname varchar(100),
second_name varchar(100),
birthday date,
passport_number varchar(50),
birth_place varchar(100),
phone varchar(30),
email varchar(50),
registration_address varchar(100),
residential_address varchar(100)
);