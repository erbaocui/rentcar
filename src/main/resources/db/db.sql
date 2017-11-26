# Host: 60.205.230.172  (Version 5.6.35-log)
# Date: 2017-11-19 11:42:58
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "sequence"
#

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='序列表，命名s_[table_name]';

#
# Structure for table "t_car"
#

DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `plate_number` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `type_id` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_car_type"
#

DROP TABLE IF EXISTS `t_car_type`;
CREATE TABLE `t_car_type` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_customer"
#

DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `type` varchar(255) DEFAULT NULL COMMENT '0个人1企业',
  `display_name` varchar(255) DEFAULT NULL COMMENT '显示名',
  `id_number` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `tax_id` varchar(255) DEFAULT NULL COMMENT '企业税号',
  `contact_mobile` varchar(255) DEFAULT NULL COMMENT '联系人手机号码',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
  `status` varchar(10) DEFAULT NULL COMMENT '0有效1无效',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_driver"
#

DROP TABLE IF EXISTS `t_driver`;
CREATE TABLE `t_driver` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_menu"
#

DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `parent_id` varchar(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_leaf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_order"
#

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `Id` varchar(32) NOT NULL DEFAULT '' COMMENT '订单ID与业务无关',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `type` varchar(255) DEFAULT NULL COMMENT '订单类型',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '用车时间',
  `tenancy` varchar(255) DEFAULT NULL COMMENT '租期',
  `start_address` varchar(255) DEFAULT NULL COMMENT '出发地点',
  `end_address` varchar(255) DEFAULT NULL COMMENT '下车地点',
  `fetch_send` varchar(255) DEFAULT NULL COMMENT '接0，送1',
  `flight_train` varchar(255) DEFAULT NULL COMMENT '航班or车次',
  `single` varchar(255) DEFAULT NULL COMMENT '单程0 往返1',
  `total` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `commission` decimal(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `customer_id` varchar(255) DEFAULT NULL COMMENT '客户id',
  `customer_type` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名',
  `customer_mobile` varchar(255) DEFAULT NULL COMMENT '客户手机号码',
  `customer_car_type` varchar(255) DEFAULT NULL COMMENT '用户选择车型',
  `supplier_id` varchar(255) DEFAULT NULL COMMENT '供应商id',
  `supplier_name` varchar(255) DEFAULT NULL COMMENT '供应商姓名',
  `car_id` varchar(32) DEFAULT NULL,
  `plate_number` varchar(255) DEFAULT NULL COMMENT '车牌',
  `car_type` varchar(255) DEFAULT NULL COMMENT '车型',
  `driver_id` varchar(32) DEFAULT NULL,
  `driver_name` varchar(255) DEFAULT NULL COMMENT '司机姓名',
  `driver_mobile` varchar(255) DEFAULT NULL COMMENT '司机电话',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_opt_id` varchar(255) DEFAULT NULL COMMENT '最近一次操作人ID',
  `last_opt_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_order_h"
#

DROP TABLE IF EXISTS `t_order_h`;
CREATE TABLE `t_order_h` (
  `Id` varchar(32) NOT NULL DEFAULT '' COMMENT '订单ID与业务无关',
  `order_id` varchar(32) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `type` varchar(255) DEFAULT NULL COMMENT '订单类型',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '用车时间',
  `tenancy` varchar(255) DEFAULT NULL COMMENT '租期',
  `start_address` varchar(255) DEFAULT NULL COMMENT '出发地点',
  `end_address` varchar(255) DEFAULT NULL COMMENT '下车地点',
  `fetch_send` varchar(255) DEFAULT NULL COMMENT '接0，送1',
  `flight_train` varchar(255) DEFAULT NULL COMMENT '航班or车次',
  `single` varchar(255) DEFAULT NULL COMMENT '单程0 往返1',
  `total` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `commission` decimal(10,2) DEFAULT NULL COMMENT '佣金',
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `customer_id` varchar(255) DEFAULT NULL COMMENT '客户id',
  `customer_type` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名',
  `customer_mobile` varchar(255) DEFAULT NULL COMMENT '客户手机号码',
  `customer_car_type` varchar(255) DEFAULT NULL COMMENT '用户选择车型',
  `supplier_id` varchar(255) DEFAULT NULL COMMENT '供应商id',
  `supplier_name` varchar(255) DEFAULT NULL COMMENT '供应商姓名',
  `car_id` varchar(32) DEFAULT NULL,
  `plate_number` varchar(255) DEFAULT NULL COMMENT '车牌',
  `car_type` varchar(255) DEFAULT NULL COMMENT '车型',
  `driver_id` varchar(32) DEFAULT NULL,
  `driver_name` varchar(255) DEFAULT NULL COMMENT '司机姓名',
  `driver_mobile` varchar(255) DEFAULT NULL COMMENT '司机电话',
  `create_time` timestamp NULL DEFAULT NULL,
  `opt_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `opt_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_picture"
#

DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `seq` int(11) DEFAULT NULL COMMENT '序号',
  `url` varchar(255) DEFAULT NULL COMMENT '图片相对地址',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_role"
#

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `kv` varchar(255) DEFAULT NULL COMMENT '标识',
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_role_menu"
#

DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `menu_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_supplier"
#

DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `status` varchar(10) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_user"
#

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `login_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Function "currval"
#

DROP FUNCTION IF EXISTS `currval`;
CREATE FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    READS SQL DATA
    DETERMINISTIC
BEGIN  
  
DECLARE VALUE INTEGER;  
  
SET VALUE = 0;  
  
SELECT current_value INTO VALUE FROM sequence WHERE NAME = seq_name;  
  
RETURN VALUE;  
  
END;

#
# Function "nextval"
#

DROP FUNCTION IF EXISTS `nextval`;
CREATE FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE sequence
          SET current_value = current_value + increment 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END;


#
# Data for table "t_menu"
#

INSERT INTO `t_menu` (`Id`,`name`,`seq`,`parent_id`,`url`,`icon`,`is_leaf`) VALUES ('1','菜单',1,'0','',NULL,'1'),('11','客户管理',1,'1',NULL,NULL,'1'),('111','个人客户',1,'11','/customer/person.do',NULL,'0'),('112','企业客户',2,'11','/customer/enterprise.do',NULL,'0'),('12','系统管理',10,'1',NULL,NULL,'1'),('121','用户管理',1,'12','/user/user.do',NULL,'0'),('122','权限管理',4,'12','/role/role.do',NULL,'0'),('13','图片管理',3,'1',NULL,NULL,'1'),('131','旅游图片管理',1,'13','/picture/tour.do',NULL,'0'),('132','签证图片管理',2,'13','/picture/visa.do',NULL,'0'),('133','房车图片管理',3,'13','/picture/rv.do',NULL,'0'),('14','供应商管理',5,'1','',NULL,'1'),('141','供应商管理',1,'14','/supplier/supplier.do',NULL,'0'),('15','司机管理',6,'1','',NULL,'1'),('151','司机管理',1,'15','/driver/driver.do',NULL,'0'),('16','车辆管理',7,'1','',NULL,'1'),('161','车辆管理',1,'16','/car/car.do',NULL,'0'),('17','订单管理',2,'1',NULL,NULL,'1'),('171','订单管理',1,'17','/order/order.do',NULL,'0'),('18','统计查询',9,'1',NULL,NULL,'1'),('181','统计查询',1,'18','/statistics/statistics.do',NULL,'0');

