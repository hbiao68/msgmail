create table k_user
(
    id               int not null auto_increment,
   name               varchar(20) ,
   age               Integer,

   primary key (id)
);

/*==============================================================*/
/* Table: AB02                                                  */
/*==============================================================*/
create table k_order
(
   id               int not null auto_increment,
   name               varchar(20), 
   address            varchar(10),
    u_id               int,
   primary key (id)
);

alter table k_order add constraint FK_Reference_1 foreign key (u_id)
      references k_user (id) on delete restrict on update restrict;
