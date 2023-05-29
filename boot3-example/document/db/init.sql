-- ds1创建
create table t_order_0
(
    order_id    bigint         not null
        primary key,
    order_no    varchar(100)   null,
    user_id     bigint         not null,
    create_name varchar(50)    null,
    price       decimal(10, 2) null
);

create table t_order_1
(
    order_id    bigint         not null
        primary key,
    order_no    varchar(100)   null,
    user_id     bigint         not null,
    create_name varchar(50)    null,
    price       decimal(10, 2) null
);


create table t_order_2
(
    order_id    bigint         not null
        primary key,
    order_no    varchar(100)   null,
    user_id     bigint         not null,
    create_name varchar(50)    null,
    price       decimal(10, 2) null
);
create table t_order_item_0
(
    item_id   bigint         not null
        primary key,
    order_id  bigint         not null,
    order_no  varchar(200)   not null,
    item_name varchar(50)    null,
    price     decimal(10, 2) null
);

create table t_order_item_1
(
    item_id   bigint         not null
        primary key,
    order_id  bigint         not null,
    order_no  varchar(200)   not null,
    item_name varchar(50)    null,
    price     decimal(10, 2) null
);
create table t_order_item_2
(
    item_id   bigint         not null
        primary key,
    order_id  bigint         not null,
    order_no  varchar(200)   not null,
    item_name varchar(50)    null,
    price     decimal(10, 2) null
);

-- ds2创建 同理上面一样  只是做了分库