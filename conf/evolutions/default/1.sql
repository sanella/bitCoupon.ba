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

create table email_verification (
  id                        varchar(255) not null,
  user_id                   bigint,
  created_on                timestamp,
  is_verified               boolean,
  constraint pk_email_verification primary key (id))
;

create table faq (
  id                        integer not null,
  title                     varchar(255),
  content                   varchar(255),
  constraint pk_faq primary key (id))
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

create sequence email_verification_seq;

create sequence faq_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists coupon;

drop table if exists email_verification;

drop table if exists faq;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists coupon_seq;

drop sequence if exists email_verification_seq;

drop sequence if exists faq_seq;

drop sequence if exists user_seq;

