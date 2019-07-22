CREATE TABLE `topic` (
  `tid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

insert into topic (tid, tname)
values (1, '幼儿绘本');
insert into topic (tid, tname)
values (2, '品德教育');
insert into topic (tid, tname)
values (3, '生命教育');
insert into topic (tid, tname)
values (4, '健康医学');
insert into topic (tid, tname)
values (5, '自然生态');
insert into topic (tid, tname)
values (6, '科学教育');
insert into topic (tid, tname)
values (7, '人文艺术');
insert into topic (tid, tname)
values (8, '历史文化');