-- --------------------------------------------------------
CREATE TABLE `TA_Category` (
  `ta_id` varchar(32) NOT NULL,
  `cateName` varchar(30) NOT NULL default '',
  `importClass` varchar(100) default NULL,
  `authClass` varchar(100) default NULL,
  PRIMARY KEY  (`ta_id`)
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX idx_ta_category ON TA_Category(cateName);

CREATE TABLE `TA_ExtendPropDef` (
  `ta_id` varchar(32) NOT NULL,
  `prop_index` int(5) NOT NULL,
  `propName` varchar(100) default NULL,
  `propDesc` varchar(100) default NULL,
  FOREIGN KEY (ta_id) REFERENCES TA_Category(ta_id) ON DELETE CASCADE
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX idx_ta_extendpropdef ON TA_ExtendPropDef(ta_id,prop_index);
CREATE UNIQUE INDEX idx_ta_extendpropdef_name ON TA_ExtendPropDef(ta_id,propName);

CREATE TABLE `TA_OrganizationDef` (
  `level_id` int(5) NOT NULL,
  `name` varchar(30) NOT NULL,  
  PRIMARY KEY  (`level_id`)
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;

CREATE TABLE `TA_Organization` (
  `orgId`   int(10) NOT NULL auto_increment,
  `orgName` varchar(30) NOT NULL,
  `level1`  varchar(30) default NULL,
  `level2`  varchar(30) default NULL,
  `level3`  varchar(30) default NULL,
  `level4`  varchar(30) default NULL,
  `level5`  varchar(30) default NULL,
  `level6`  varchar(30) default NULL
  PRIMARY KEY ('orgId')
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;

CREATE TABLE `TA_App` (
  `appId`   int(10) NOT NULL auto_increment,
  `appName` varchar(30) NOT NULL,
  `appDomain`  varchar(100) NOT NULL,
  `ta_id` varchar(32) NOT NULL,
  FOREIGN KEY (ta_id) REFERENCES TA_Category(ta_id) ON DELETE CASCADE
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;

-- ---------------------------------------------------
-- 在创建一个categroy的时候可以直接创建这样的一个Table -----------
CREATE TABLE `TA_Account_XXXXXX` (
  `Id`   int(20) NOT NULL auto_increment,
  `accountName` varchar(30) NOT NULL,
  `accountPwd` varchar(30) Default NULL,
  `email`  varchar(100) NOT NULL,
  `orgId` int(10) NOT NULL,
  FOREIGN KEY (orgId) REFERENCES TA_Organization(orgId)
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX idx_TA_Account_XXXXXX ON TA_Account_XXXXXX(accountName);


CREATE TABLE `TA_ExtendProp_XXXXXX` (
  `Ta_extId`   int(20) NOT NULL,
  `prop1`  varchar(50) default NULL,
  `prop2`  varchar(50) default NULL,
  `prop3`  varchar(50) default NULL,
  `prop4`  varchar(50) default NULL,
  `prop5`  varchar(50) default NULL,
  `prop6`  varchar(50) default NULL,
  `prop7`  varchar(50) default NULL,
  `prop8`  varchar(50) default NULL,
  `prop9`  varchar(50) default NULL,
  `prop10`  varchar(50) default NULL,
  FOREIGN KEY (Ta_extId) REFERENCES TA_Account_XXXXXX(Id) ON DELETE CASCADE
) ENGINE=ndbcluster DEFAULT CHARSET=utf8;

