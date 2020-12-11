create table deal_table (deal_id int8 not null, deal_date date, deal_price numeric(19, 2), deal_quantity numeric(19, 2), deal_buyer_entity_fk int8, deal_item_item_fk int8, deal_seller_entity_fk int8, primary key (deal_id));

create table entity_table (entity_id int8 not null, entity_name varchar(255), primary key (entity_id));

create table item_table (item_id int8 not null, item_name varchar(255), item_unit_unit_fk int8, primary key (item_id));

create table unit_table (unit_id int8 not null, unit_name varchar(255), primary key (unit_id));

create table user_table (user_id int8 not null, user_password varchar(255), user_username varchar(255), primary key (user_id));

create sequence deal_seq start 1 increment 1;

create sequence entity_seq start 1 increment 1;

create sequence item_seq start 1 increment 1;

create sequence unit_seq start 1 increment 1;

create sequence user_seq start 1 increment 1;

alter table if exists deal_table add constraint FKi9kmgkd2ivc9957192gv6adt7 foreign key (deal_buyer_entity_fk) references entity_table;

alter table if exists deal_table add constraint FK3b6ks6od1cq1cymvyknglswk0 foreign key (deal_item_item_fk) references item_table;

alter table if exists deal_table add constraint FKt9twq3tc6g9uwwl28jqxmcj5s foreign key (deal_seller_entity_fk) references entity_table;

alter table if exists item_table add constraint FKg9c4dp6e8fwohwr5jgjgbvc2u foreign key (item_unit_unit_fk) references unit_table;
