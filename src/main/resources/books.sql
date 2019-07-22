CREATE TABLE `books` (
  `bid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `btid` int(11) NOT NULL,
  `bname` varchar(500) NOT NULL,
  `bauthor` char(50) NOT NULL,
  `bpublisher` char(50) DEFAULT NULL,
  `bcreatedate` char(50) DEFAULT NULL,
  `bsummary` varchar(4000) DEFAULT NULL,
  `bprice` float DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8