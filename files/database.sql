create table authority
(
  id           bigint(32) auto_increment
    primary key,
  resource_id  bigint(32) not null,
  operation_id int        null,
  create_time  datetime   null
);


create table operation
(
  id   int auto_increment
    primary key,
  name varchar(255) null,
  tag  varchar(255) null
);


create table resource
(
  id          bigint(32) auto_increment
    primary key,
  name        varchar(255) null,
  tag         varchar(255) null,
  create_time datetime     null,
  parent_id   bigint       null
);


create table resource_operation
(
  id           bigint(32) auto_increment
    primary key,
  resource_id  bigint(32) null,
  operation_id int        null
);


create table role
(
  id          bigint(32) auto_increment
    primary key,
  name        varchar(255) null,
  create_time datetime     null
);


create table role_authority
(
  id           bigint(32) auto_increment
    primary key,
  role_id      bigint(32) not null,
  authority_id bigint(32) not null
);


create table user
(
  id          bigint(32) auto_increment
    primary key,
  name        varchar(255)         null,
  email       varchar(255)         null,
  password    varchar(255)         null,
  create_time datetime             null,
  enabled     tinyint(1) default 1 null
);

INSERT INTO test.user (id, name, email, password, create_time, enabled)
VALUES (1, 'Admin', 'admin', '$2a$10$eLN0qc4u22eSMQn1bbXTVuyQ/DmjEOqFigsFuwFgJvElg7VW/iNcu', '2019-02-01 08:08:15', 1);
create table user_role
(
  id      bigint(32) auto_increment
    primary key,
  user_id bigint(32) not null,
  role_id bigint(32) not null
);

