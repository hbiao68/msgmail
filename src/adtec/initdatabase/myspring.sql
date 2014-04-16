create  database  if  not  exists  myspring default character set utf8;
USE myspring;
SET FOREIGN_KEY_CHECKS=0;

 /* 以下为权限管理系统所用表的sql语句 */ 


DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resid` char(255) NOT NULL,
  `resname` varchar(255) DEFAULT NULL,
  `commen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `resource` VALUES ('0297c16fca3048d59dc5c4c1353aecfd', '业务开通', '');
INSERT INTO `resource` VALUES ('3bc42cdf946b4feab4029a581de70bd4', '发送消息所用帐号', '');
INSERT INTO `resource` VALUES ('5d54cee532254661b117a28afe5c93d5', '终端分类管理', '');
INSERT INTO `resource` VALUES ('66315a4ebb294a5988132d2101e8f67e', '机构业务开通', '');
INSERT INTO `resource` VALUES ('9bcb70eb2934407cb78b55de5aa5007b', '业务管理', '');
INSERT INTO `resource` VALUES ('9ddb74adf44d49a8a9bc17baa23ee16d', '机构分级设置', '');
INSERT INTO `resource` VALUES ('a8f8f774f259465cbd03a434c4e8c4f7', '终端帐号管理', '');
INSERT INTO `resource` VALUES ('b0e5463027f14655aa594660f22f2615', '用户管理资源', '');
INSERT INTO `resource` VALUES ('b749f044c3034df282b1a05f49604dfc', '机构帐号管理', '');
INSERT INTO `resource` VALUES ('e41267ff972c4ef7bc30a2d158028eae', '权限管理资源', '');
INSERT INTO `resource` VALUES ('e8f3d48f3f264dae921eea7b71365a51', '机构导入', '');
INSERT INTO `resource` VALUES ('ff8e8500491f4d43aba2478db07a6186', '发送消息', '');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` char(255) NOT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `roleprivilege`;
CREATE TABLE `roleprivilege` (
  `roleplgid` char(255) NOT NULL,
  `roleid` char(255) NOT NULL,
  `privilegeid` char(255) NOT NULL,
  PRIMARY KEY (`roleplgid`),
  KEY `roleid` (`roleid`),
  KEY `privilegeid` (`privilegeid`),
  CONSTRAINT `roleprivilege_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`),
  CONSTRAINT `roleprivilege_ibfk_2` FOREIGN KEY (`privilegeid`) REFERENCES `privilege` (`privilegeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeid` char(255) NOT NULL,
  `typename` varchar(255) DEFAULT NULL,
  `common` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `type` VALUES ('1001', '查看', null);
INSERT INTO `type` VALUES ('1002', '添加', null);
INSERT INTO `type` VALUES ('1003', '修改', null);
INSERT INTO `type` VALUES ('1004', '删除', null);


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` char(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES ('816ff9b5c580422a80e78dffe70e98c6', 'admin', '21232f297a57a5a743894a0e4a801fc3');


DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `privilegeid` char(255) NOT NULL,
  `resid` char(255) NOT NULL,
  `actiontype` char(255) DEFAULT NULL,
  `actionvalue` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`privilegeid`),
  KEY `resid` (`resid`),
  KEY `actiontype` (`actiontype`),
  CONSTRAINT `privilege_ibfk_2` FOREIGN KEY (`actiontype`) REFERENCES `type` (`typeid`),
  CONSTRAINT `privilege_ibfk_1` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `privilege` VALUES ('091376ef5cf2449fb4f9e34a13f6f511', '9bcb70eb2934407cb78b55de5aa5007b', '1004', '1');
INSERT INTO `privilege` VALUES ('13d6fd5d22744fcb9acb4f5a277eadf6', '9bcb70eb2934407cb78b55de5aa5007b', '1002', '1');
INSERT INTO `privilege` VALUES ('16abe085a78c4d4fa2404fa4cdb6dd47', '3bc42cdf946b4feab4029a581de70bd4', '1001', '1');
INSERT INTO `privilege` VALUES ('1aa8dacb0f194cde9ef7df2a72740f82', '9ddb74adf44d49a8a9bc17baa23ee16d', '1003', '1');
INSERT INTO `privilege` VALUES ('1dd1cae3fbbf4f69b0cd3c6a6488cddc', '9bcb70eb2934407cb78b55de5aa5007b', '1003', '1');
INSERT INTO `privilege` VALUES ('225db88c0970438497ed67f8b523e44b', 'b0e5463027f14655aa594660f22f2615', '1002', '1');
INSERT INTO `privilege` VALUES ('33dda911bebe42b39209352c0b25a6f0', '3bc42cdf946b4feab4029a581de70bd4', '1002', '1');
INSERT INTO `privilege` VALUES ('38fe85db5a6c4af4a5b2f6a006baa3e6', '3bc42cdf946b4feab4029a581de70bd4', '1004', '1');
INSERT INTO `privilege` VALUES ('4320b8ce67be4ebdb7a05eab67f673a3', '9ddb74adf44d49a8a9bc17baa23ee16d', '1001', '1');
INSERT INTO `privilege` VALUES ('4741e8e4b40e42dd9a892da709a14e21', '5d54cee532254661b117a28afe5c93d5', '1003', '1');
INSERT INTO `privilege` VALUES ('48763555c58b4f19bc022c6225da4b84', 'a8f8f774f259465cbd03a434c4e8c4f7', '1002', '1');
INSERT INTO `privilege` VALUES ('50e1e469df764beaadeed497af2c84da', '5d54cee532254661b117a28afe5c93d5', '1002', '1');
INSERT INTO `privilege` VALUES ('63f95e3eb995465bb19c9aa061a16b7c', 'ff8e8500491f4d43aba2478db07a6186', '1001', '1');
INSERT INTO `privilege` VALUES ('65bbc163acf74cb09ed9ac9697212c3b', 'e8f3d48f3f264dae921eea7b71365a51', '1003', '1');
INSERT INTO `privilege` VALUES ('68f93ea64f504a3ca86c88bef8f2d8dc', 'ff8e8500491f4d43aba2478db07a6186', '1003', '1');
INSERT INTO `privilege` VALUES ('6b95621979954e7588d0c324fdb545cb', 'b0e5463027f14655aa594660f22f2615', '1003', '1');
INSERT INTO `privilege` VALUES ('6bf8098f5b0f42a8baa4a37d8ed53b31', '0297c16fca3048d59dc5c4c1353aecfd', '1001', '1');
INSERT INTO `privilege` VALUES ('6fb035ee39894060922aaf4237eb7ea9', 'b749f044c3034df282b1a05f49604dfc', '1003', '1');
INSERT INTO `privilege` VALUES ('74a0764009ee440788f6ed954cf3c3b9', 'e41267ff972c4ef7bc30a2d158028eae', '1003', '1');
INSERT INTO `privilege` VALUES ('7b5c974a639141b5802f5575a8255a4c', 'e41267ff972c4ef7bc30a2d158028eae', '1002', '1');
INSERT INTO `privilege` VALUES ('7b9cdd77ac704838a2b966479984a5e1', 'ff8e8500491f4d43aba2478db07a6186', '1002', '1');
INSERT INTO `privilege` VALUES ('8379792b04814ea28366f2f5f2a7738c', 'e8f3d48f3f264dae921eea7b71365a51', '1001', '1');
INSERT INTO `privilege` VALUES ('9050e242d76240ad916200c6f7311ba9', 'e41267ff972c4ef7bc30a2d158028eae', '1004', '1');
INSERT INTO `privilege` VALUES ('905158bbe3204b29befab30e0a5c4f17', '5d54cee532254661b117a28afe5c93d5', '1001', '1');
INSERT INTO `privilege` VALUES ('95397c33179f4d31addcf83006ad7e4c', 'e8f3d48f3f264dae921eea7b71365a51', '1002', '1');
INSERT INTO `privilege` VALUES ('97ae7962934745bf96846f21c30d4aa9', '0297c16fca3048d59dc5c4c1353aecfd', '1004', '1');
INSERT INTO `privilege` VALUES ('97da03d9b0834cb7a53d3c04960edd5d', 'a8f8f774f259465cbd03a434c4e8c4f7', '1003', '1');
INSERT INTO `privilege` VALUES ('9a8f57d8f8f3480b8b1acf418322c7db', '0297c16fca3048d59dc5c4c1353aecfd', '1002', '1');
INSERT INTO `privilege` VALUES ('9ec3ab85184940dd9c8d2dae2c4dc572', '66315a4ebb294a5988132d2101e8f67e', '1001', '1');
INSERT INTO `privilege` VALUES ('a19d42e9b4324f3ba41f2d3c11643e72', 'a8f8f774f259465cbd03a434c4e8c4f7', '1001', '1');
INSERT INTO `privilege` VALUES ('a4906de5bb9645089c9ced84526dd2df', 'b0e5463027f14655aa594660f22f2615', '1004', '1');
INSERT INTO `privilege` VALUES ('be3fcfd7417d4156aba1ad8f3605b277', '3bc42cdf946b4feab4029a581de70bd4', '1003', '1');
INSERT INTO `privilege` VALUES ('c0c7e48ec8564d58a64e36556c588d2f', 'ff8e8500491f4d43aba2478db07a6186', '1004', '1');
INSERT INTO `privilege` VALUES ('c4923214f82a484694cb6d662b6e0f2c', '66315a4ebb294a5988132d2101e8f67e', '1002', '1');
INSERT INTO `privilege` VALUES ('c6fde87062644fcaba57da667e9ee999', '5d54cee532254661b117a28afe5c93d5', '1004', '1');
INSERT INTO `privilege` VALUES ('cba804c4d9874901b67ed8f1d6ea9f2c', '9ddb74adf44d49a8a9bc17baa23ee16d', '1002', '1');
INSERT INTO `privilege` VALUES ('cd22c5bc7ea34a63a2939b12b38a7cb9', 'b749f044c3034df282b1a05f49604dfc', '1002', '1');
INSERT INTO `privilege` VALUES ('d6ac65fdb6354ceabb8bf92f8489f705', 'b749f044c3034df282b1a05f49604dfc', '1001', '1');
INSERT INTO `privilege` VALUES ('d73ffe1c95e942d7b842f49ed2334ffb', '66315a4ebb294a5988132d2101e8f67e', '1004', '1');
INSERT INTO `privilege` VALUES ('dbf7873df40a4cdabd355486e14b5649', '0297c16fca3048d59dc5c4c1353aecfd', '1003', '1');
INSERT INTO `privilege` VALUES ('eac6657401f8471f97d764bf4e13c402', 'b749f044c3034df282b1a05f49604dfc', '1004', '1');
INSERT INTO `privilege` VALUES ('f2eeff329d9a4afb8653d97b88244578', 'b0e5463027f14655aa594660f22f2615', '1001', '1');
INSERT INTO `privilege` VALUES ('f3e0418b4bae4949a050cd2ba10df8c5', 'e41267ff972c4ef7bc30a2d158028eae', '1001', '1');
INSERT INTO `privilege` VALUES ('f8b348cedf294c9c8e3c4dcaa6720273', 'a8f8f774f259465cbd03a434c4e8c4f7', '1004', '1');
INSERT INTO `privilege` VALUES ('f8d80e6eda084846ac8a1bf8cbdbf53a', '9bcb70eb2934407cb78b55de5aa5007b', '1001', '1');
INSERT INTO `privilege` VALUES ('f900dc0206c84e139c7026eb558c92d2', '66315a4ebb294a5988132d2101e8f67e', '1003', '1');
INSERT INTO `privilege` VALUES ('f944a0d45de94aee9d4f917adf2937a7', '9ddb74adf44d49a8a9bc17baa23ee16d', '1004', '1');
INSERT INTO `privilege` VALUES ('fcc9a2fd921b48069cdcbb998eaaf1c9', 'e8f3d48f3f264dae921eea7b71365a51', '1004', '1');


DROP TABLE IF EXISTS `userprivilege`;
CREATE TABLE `userprivilege` (
  `userplgid` char(255) NOT NULL,
  `userid` char(255) NOT NULL,
  `privilegeid` char(255) NOT NULL,
  PRIMARY KEY (`userplgid`),
  KEY `userid` (`userid`),
  KEY `privilegeid` (`privilegeid`),
  CONSTRAINT `userprivilege_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userprivilege_ibfk_2` FOREIGN KEY (`privilegeid`) REFERENCES `privilege` (`privilegeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `userprivilege` VALUES ('00be55c0d2f44defb423970569a958f9', '816ff9b5c580422a80e78dffe70e98c6', '1dd1cae3fbbf4f69b0cd3c6a6488cddc');
INSERT INTO `userprivilege` VALUES ('093eef2790dc48a4905e11e5c6179776', '816ff9b5c580422a80e78dffe70e98c6', '63f95e3eb995465bb19c9aa061a16b7c');
INSERT INTO `userprivilege` VALUES ('0b63cf599715465b980de71b2508dee0', '816ff9b5c580422a80e78dffe70e98c6', '33dda911bebe42b39209352c0b25a6f0');
INSERT INTO `userprivilege` VALUES ('168f486c32df4c2f93e8caeec7b36171', '816ff9b5c580422a80e78dffe70e98c6', '225db88c0970438497ed67f8b523e44b');
INSERT INTO `userprivilege` VALUES ('1b18706a5a7e433caeb8930c0fa8af0d', '816ff9b5c580422a80e78dffe70e98c6', 'f900dc0206c84e139c7026eb558c92d2');
INSERT INTO `userprivilege` VALUES ('202ac72b89af40ee94703f202381ff49', '816ff9b5c580422a80e78dffe70e98c6', '9050e242d76240ad916200c6f7311ba9');
INSERT INTO `userprivilege` VALUES ('2196915437614c9b913f985285cf4c8c', '816ff9b5c580422a80e78dffe70e98c6', '905158bbe3204b29befab30e0a5c4f17');
INSERT INTO `userprivilege` VALUES ('25a7359614634ae5b83878bd340372a3', '816ff9b5c580422a80e78dffe70e98c6', '4741e8e4b40e42dd9a892da709a14e21');
INSERT INTO `userprivilege` VALUES ('2afc69947b034af6b1c8066e66cda8fd', '816ff9b5c580422a80e78dffe70e98c6', 'c6fde87062644fcaba57da667e9ee999');
INSERT INTO `userprivilege` VALUES ('32be951a18854e8d833da9d3776511e3', '816ff9b5c580422a80e78dffe70e98c6', 'dbf7873df40a4cdabd355486e14b5649');
INSERT INTO `userprivilege` VALUES ('358e4c3bee09429f945c3a87e5ad7c52', '816ff9b5c580422a80e78dffe70e98c6', '13d6fd5d22744fcb9acb4f5a277eadf6');
INSERT INTO `userprivilege` VALUES ('3a48fd6319294dcfbca2a12ca312c0b7', '816ff9b5c580422a80e78dffe70e98c6', 'f944a0d45de94aee9d4f917adf2937a7');
INSERT INTO `userprivilege` VALUES ('40130296f8434e47970f4e05b96bbeae', '816ff9b5c580422a80e78dffe70e98c6', '8379792b04814ea28366f2f5f2a7738c');
INSERT INTO `userprivilege` VALUES ('441a000440b44ffe8483a3396fe9b7a9', '816ff9b5c580422a80e78dffe70e98c6', 'f8b348cedf294c9c8e3c4dcaa6720273');
INSERT INTO `userprivilege` VALUES ('465f2d1999b1464eb3188b8002a001a5', '816ff9b5c580422a80e78dffe70e98c6', 'f2eeff329d9a4afb8653d97b88244578');
INSERT INTO `userprivilege` VALUES ('4954bebfd9644addaf3b488a201e744e', '816ff9b5c580422a80e78dffe70e98c6', '9ec3ab85184940dd9c8d2dae2c4dc572');
INSERT INTO `userprivilege` VALUES ('4ef28d3d74f24f2bb6a6e63e675dc987', '816ff9b5c580422a80e78dffe70e98c6', '9a8f57d8f8f3480b8b1acf418322c7db');
INSERT INTO `userprivilege` VALUES ('554948c2dc7d49c1a0cd685753663f1b', '816ff9b5c580422a80e78dffe70e98c6', '48763555c58b4f19bc022c6225da4b84');
INSERT INTO `userprivilege` VALUES ('586d29ed716e47c695ab482024ac74bc', '816ff9b5c580422a80e78dffe70e98c6', '97ae7962934745bf96846f21c30d4aa9');
INSERT INTO `userprivilege` VALUES ('6dc64cdd8b7a428b9151d300fa039c26', '816ff9b5c580422a80e78dffe70e98c6', '7b9cdd77ac704838a2b966479984a5e1');
INSERT INTO `userprivilege` VALUES ('7ab30b848ae54c379960ccc1b5b517a6', '816ff9b5c580422a80e78dffe70e98c6', '68f93ea64f504a3ca86c88bef8f2d8dc');
INSERT INTO `userprivilege` VALUES ('7ab9fcd2b58a4219b862544580ccd92e', '816ff9b5c580422a80e78dffe70e98c6', '74a0764009ee440788f6ed954cf3c3b9');
INSERT INTO `userprivilege` VALUES ('7fa72b3210774edab17f26100b008017', '816ff9b5c580422a80e78dffe70e98c6', 'c0c7e48ec8564d58a64e36556c588d2f');
INSERT INTO `userprivilege` VALUES ('80029ba85117477aae74fc69e2e32542', '816ff9b5c580422a80e78dffe70e98c6', '091376ef5cf2449fb4f9e34a13f6f511');
INSERT INTO `userprivilege` VALUES ('815029d1fdd34eccba54683e23a3e18a', '816ff9b5c580422a80e78dffe70e98c6', 'fcc9a2fd921b48069cdcbb998eaaf1c9');
INSERT INTO `userprivilege` VALUES ('818f224efec64546abf20577317144be', '816ff9b5c580422a80e78dffe70e98c6', '95397c33179f4d31addcf83006ad7e4c');
INSERT INTO `userprivilege` VALUES ('826e432169704a31906d881ba4148c8d', '816ff9b5c580422a80e78dffe70e98c6', '6fb035ee39894060922aaf4237eb7ea9');
INSERT INTO `userprivilege` VALUES ('849a265223ed416fa590d3f786963733', '816ff9b5c580422a80e78dffe70e98c6', 'a4906de5bb9645089c9ced84526dd2df');
INSERT INTO `userprivilege` VALUES ('8593f265feeb4949b61ac0c457c13ea7', '816ff9b5c580422a80e78dffe70e98c6', '6bf8098f5b0f42a8baa4a37d8ed53b31');
INSERT INTO `userprivilege` VALUES ('87858edbab5c4736b813e3a4ea030c15', '816ff9b5c580422a80e78dffe70e98c6', '6b95621979954e7588d0c324fdb545cb');
INSERT INTO `userprivilege` VALUES ('8a30479f3c464a20ab0b6c3368c56028', '816ff9b5c580422a80e78dffe70e98c6', '65bbc163acf74cb09ed9ac9697212c3b');
INSERT INTO `userprivilege` VALUES ('8bb95eb36ecd43ce8e8220778c6795ea', '816ff9b5c580422a80e78dffe70e98c6', 'd73ffe1c95e942d7b842f49ed2334ffb');
INSERT INTO `userprivilege` VALUES ('8c16243294914fceb1c3fb5e3f759023', '816ff9b5c580422a80e78dffe70e98c6', 'f3e0418b4bae4949a050cd2ba10df8c5');
INSERT INTO `userprivilege` VALUES ('8c8f316513d441e790e266b843dbf37e', '816ff9b5c580422a80e78dffe70e98c6', '1aa8dacb0f194cde9ef7df2a72740f82');
INSERT INTO `userprivilege` VALUES ('905aec9c5266439f877c72dc5b310187', '816ff9b5c580422a80e78dffe70e98c6', '16abe085a78c4d4fa2404fa4cdb6dd47');
INSERT INTO `userprivilege` VALUES ('905b2cfe04ca47888872c3f8466fb1bd', '816ff9b5c580422a80e78dffe70e98c6', 'd6ac65fdb6354ceabb8bf92f8489f705');
INSERT INTO `userprivilege` VALUES ('97c1d6a65e4140b985d1c8685afadff8', '816ff9b5c580422a80e78dffe70e98c6', '97da03d9b0834cb7a53d3c04960edd5d');
INSERT INTO `userprivilege` VALUES ('9cc4a770f7b943adaca6e02d8bb0cc8f', '816ff9b5c580422a80e78dffe70e98c6', 'cd22c5bc7ea34a63a2939b12b38a7cb9');
INSERT INTO `userprivilege` VALUES ('abcf802a0b2c427c990a298dddb2208a', '816ff9b5c580422a80e78dffe70e98c6', 'be3fcfd7417d4156aba1ad8f3605b277');
INSERT INTO `userprivilege` VALUES ('b845629409ba4b67a9de2615a52adeb3', '816ff9b5c580422a80e78dffe70e98c6', 'eac6657401f8471f97d764bf4e13c402');
INSERT INTO `userprivilege` VALUES ('bfee2b2febc0449f8b46110d224f2c5c', '816ff9b5c580422a80e78dffe70e98c6', '4320b8ce67be4ebdb7a05eab67f673a3');
INSERT INTO `userprivilege` VALUES ('c0e46fab15f04625b765508e182803f2', '816ff9b5c580422a80e78dffe70e98c6', '7b5c974a639141b5802f5575a8255a4c');
INSERT INTO `userprivilege` VALUES ('cab9ea41a4a543af9f421201da7c1936', '816ff9b5c580422a80e78dffe70e98c6', 'c4923214f82a484694cb6d662b6e0f2c');
INSERT INTO `userprivilege` VALUES ('d83d09ff329f4bf0ad4309f26f146662', '816ff9b5c580422a80e78dffe70e98c6', '38fe85db5a6c4af4a5b2f6a006baa3e6');
INSERT INTO `userprivilege` VALUES ('ddc751e2e8254b8c87c537926534ae77', '816ff9b5c580422a80e78dffe70e98c6', 'a19d42e9b4324f3ba41f2d3c11643e72');
INSERT INTO `userprivilege` VALUES ('e1aad7e81cc843d9b313780053d40ec8', '816ff9b5c580422a80e78dffe70e98c6', '50e1e469df764beaadeed497af2c84da');
INSERT INTO `userprivilege` VALUES ('f5101688ca7a4d46a397a38c45ee663a', '816ff9b5c580422a80e78dffe70e98c6', 'f8d80e6eda084846ac8a1bf8cbdbf53a');
INSERT INTO `userprivilege` VALUES ('f79b6223fb0f4216bce38f9c5d7d6b8e', '816ff9b5c580422a80e78dffe70e98c6', 'cba804c4d9874901b67ed8f1d6ea9f2c');


DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `userroleid` char(255) NOT NULL,
  `userid` char(255) NOT NULL,
  `roleid` char(255) NOT NULL,
  PRIMARY KEY (`userroleid`),
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `userrole_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `menucolumn`;
CREATE TABLE `menucolumn` (
  `columnid` char(255) NOT NULL,
  `columnname` varchar(255) DEFAULT NULL,
  `columnurl` varchar(255) DEFAULT NULL,
  `resid` varchar(255) NOT NULL,
  PRIMARY KEY (`columnid`),
  KEY `resid` (`resid`),
  CONSTRAINT `menucolumn_ibfk_1` FOREIGN KEY (`resid`) REFERENCES `resource` (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `menucolumn` VALUES ('0307ea432fd24bc392fe8fab3d0132dc', '业务管理', 'appManager/appList.do', '9bcb70eb2934407cb78b55de5aa5007b');
INSERT INTO `menucolumn` VALUES ('11306218534048249677372a1bbb2c85', '业务开通', 'relation/relationList.do', '0297c16fca3048d59dc5c4c1353aecfd');
INSERT INTO `menucolumn` VALUES ('1b4bdcc2363444dab5a744aacf8cb7af', '机构分级设置', 'organizationDefManager/queryAllOrgDef.do', '9ddb74adf44d49a8a9bc17baa23ee16d');
INSERT INTO `menucolumn` VALUES ('2c743681acc84782978f940d20370356', '发送消息所用帐号', 'account/accList.do', '3bc42cdf946b4feab4029a581de70bd4');
INSERT INTO `menucolumn` VALUES ('4e6331fd9ced42d5ab5aea56ac4d9263', '终端分类管理', 'category/cateList.do', '5d54cee532254661b117a28afe5c93d5');
INSERT INTO `menucolumn` VALUES ('6e8bb4b5c9464b3483d6316f64e342b3', '机构业务开通', 'ta_org_relation/orgRelationList.do', '66315a4ebb294a5988132d2101e8f67e');
INSERT INTO `menucolumn` VALUES ('76f440fb67284cbea774c485e6d5e734', '机构导入', 'organizationManager/orgList.do', 'e8f3d48f3f264dae921eea7b71365a51');
INSERT INTO `menucolumn` VALUES ('96822e5a19644bb2a58b167fe5d30cc4', '终端帐号管理', 'accountManager/accountList.do', 'a8f8f774f259465cbd03a434c4e8c4f7');
INSERT INTO `menucolumn` VALUES ('96ffe720baed4c75b08665e88f9f0722', '发送消息', 'adtec_message/imjwchat.do', 'ff8e8500491f4d43aba2478db07a6186');
INSERT INTO `menucolumn` VALUES ('a1f2e2b3612c44fdbcfc153a6fc27d3d', '机构帐号管理', 'accorgrelation/accorgList.do', 'b749f044c3034df282b1a05f49604dfc');
INSERT INTO `menucolumn` VALUES ('a8307575dd2a469d9ac95f7a77503c3d', '权限管理', 'privilege/privilegeManagerList.do', 'e41267ff972c4ef7bc30a2d158028eae');
INSERT INTO `menucolumn` VALUES ('cee22a4d4a3e4af48a687bf56ea83346', '用户管理', 'user/list.do', 'b0e5463027f14655aa594660f22f2615');



/* 以下为消息邮局所用表的sql语句 */ 

DROP TABLE IF EXISTS `ta_category`;
CREATE TABLE `ta_category` (
  `ta_id` varchar(32) NOT NULL,
  `cateName` varchar(30) NOT NULL DEFAULT '' COMMENT '端终名称',
  `importClass` varchar(100) DEFAULT NULL COMMENT '导入类',
  `authClass` varchar(100) DEFAULT NULL COMMENT '认证类',
  `cateDesc` varchar(64) DEFAULT NULL COMMENT '终端中文描述',
  PRIMARY KEY (`ta_id`),
  UNIQUE KEY `idx_ta_category` (`cateName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ta_extendpropdef`;
CREATE TABLE `ta_extendpropdef` (
  `propdefid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ta_id` varchar(32) NOT NULL,
  `prop_index` int(5) NOT NULL,
  `propName` varchar(100) DEFAULT NULL,
  `propDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`propdefid`),
  UNIQUE KEY `idx_ta_extendpropdef` (`ta_id`,`prop_index`),
  UNIQUE KEY `idx_ta_extendpropdef_name` (`ta_id`,`propName`),
  CONSTRAINT `ta_extendpropdef_ibfk_1` FOREIGN KEY (`ta_id`) REFERENCES `ta_category` (`ta_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ta_app`;
CREATE TABLE `ta_app` (
  `appId` int(10) NOT NULL AUTO_INCREMENT,
  `appName` varchar(30) NOT NULL,
  `appDomain` varchar(100) NOT NULL,
  `ta_id` varchar(32) NOT NULL,
  PRIMARY KEY (`appId`),
  KEY `ta_id` (`ta_id`),
  CONSTRAINT `ta_app_ibfk_1` FOREIGN KEY (`ta_id`) REFERENCES `ta_category` (`ta_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ta_app_relation`;
CREATE TABLE `ta_app_relation` (
  `relationid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `accountName` varchar(30) NOT NULL COMMENT '账号名称',
  `appid` int(10) NOT NULL COMMENT '用应ID',
  `ta_id` varchar(32) NOT NULL,
  PRIMARY KEY (`relationid`),
  KEY `FK_Reference_2` (`appid`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`appid`) REFERENCES `ta_app` (`appId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ta_organization`;
CREATE TABLE `ta_organization` (
  `orgId` int(10) NOT NULL AUTO_INCREMENT,
  `orgName` varchar(30) NOT NULL,
  `level1` varchar(30) DEFAULT NULL,
  `level2` varchar(30) DEFAULT NULL,
  `level3` varchar(30) DEFAULT NULL,
  `level4` varchar(30) DEFAULT NULL,
  `level5` varchar(30) DEFAULT NULL,
  `level6` varchar(30) DEFAULT NULL,
  `level7` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`orgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ta_organizationdef`;
CREATE TABLE `ta_organizationdef` (
  `level_id` int(5) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ta_org_relation`;
CREATE TABLE `ta_org_relation` (
  `orgRelationId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `orgId` int(10) NOT NULL COMMENT '机构ID',
  `appid` int(10) NOT NULL COMMENT '应用ID',
  `ta_id` varchar(32) NOT NULL,
  PRIMARY KEY (`orgRelationId`),
  KEY `FK_Reference_4` (`appid`),
  KEY `FK_Reference_3` (`orgId`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`orgId`) REFERENCES `ta_organization` (`orgId`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`appid`) REFERENCES `ta_app` (`appId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=751 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `username` varchar(64) NOT NULL,
  `plainpassword` varchar(32) DEFAULT NULL,
  `encryptedpassword` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `creationdate` char(15) NOT NULL,
  `modificationdate` char(15) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `ofUser_cDate_idx` (`creationdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `accountorgrelation`;
CREATE TABLE `accountorgrelation` (
  `relationid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `orgId` int(10) NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`relationid`),
  KEY `FK_Reference_5` (`orgid`),
  KEY `FK_Reference_6` (`username`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`orgid`) REFERENCES `ta_organization` (`orgId`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`username`) REFERENCES `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
