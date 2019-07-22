create table userinfo
(
  uid  int unsigned NOT NULL AUTO_INCREMENT,
  uname varchar(20) not null,
  upwd  varchar(20),
  PRIMARY KEY (uid)
);

insert into userinfo (uid, uname, upwd) values (1, 'admin', 'admin');
insert into userinfo (uid, uname, upwd) values (2, 'root', 'root');