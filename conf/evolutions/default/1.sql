# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table coupon (
  id                        bigint not null,
  name                      varchar(255),
  price                     double,
  created_at                timestamp,
  expired_at                timestamp,
  picture                   varchar(255),
  category                  varchar(255),
  description               varchar(255),
  remark                    varchar(255),
  constraint pk_coupon primary key (id))
;

create table user (
  id                        bigint not null,
  username                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  is_admin                  boolean,
  created                   timestamp,
  updated                   timestamp,
  constraint pk_user primary key (id))
;

create sequence coupon_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists coupon;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists coupon_seq;

drop sequence if exists user_seq;

