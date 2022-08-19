create table table_order
(
    order_id   integer primary key,
    product_nm varchar(400) not null,
    order_type varchar(20)  not null
);

CREATE SEQUENCE order_sequence START with 3 increment by 1;