CREATE TABLE IF NOT EXISTS `xx_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `create_time` bigint unsigned NOT NULL COMMENT '创建时间',
  `update_time` bigint unsigned NOT NULL COMMENT '更新时间',
  `username` VARCHAR(64) NOT NULL UNIQUE COMMENT '用户名',
  `password` CHAR(16) NOT NULL COMMENT '密码',
  `email` VARCHAR(64) NOT NULL UNIQUE COMMENT '邮箱',
  `age` TINYINT UNSIGNED NOT NULL DEFAULT 18 COMMENT '年龄',
  `sex` ENUM('man','woman','baomi') NOT NULL DEFAULT 'baomi' COMMENT '性别',
  `tel` CHAR(11) NOT NULL UNIQUE COMMENT '电话',
  `addr` VARCHAR(256) NOT NULL DEFAULT 'beijing' COMMENT '地址',
  `card` CHAR(18) NOT NULL UNIQUE COMMENT '身份证号',
  `married` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0代表未结婚，1代表已结婚',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `xx_goods` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `create_time` bigint unsigned NOT NULL COMMENT '创建时间',
  `update_time` bigint unsigned NOT NULL COMMENT '更新时间',
  `name` VARCHAR(64) NOT NULL COMMENT '商品名称',
  `category_id` int unsigned not null default 0 comment’分类id’,
  `price` decimal(10,2)unsigned not null default 0 comment ‘价格’,
  `stock` int unsigned not null default 0 comment ‘库存’,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

CREATE TABLE IF NOT EXISTS `xx_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `create_time` bigint unsigned NOT NULL COMMENT '创建时间',
  `update_time` bigint unsigned NOT NULL COMMENT '更新时间',
  `order_no` varchar(40) NOT NULL COMMENT '订单号',
  `user_id` int NOT NULL COMMENT '用户id',
  `good_id` int NOT NULL COMMENT '商品id',
  `good_price` int NOT NULL COMMENT '商品价格：分',
  `count` int NOT NULL COMMENT '商品数量',
  `amount` int NOT NULL DEFAULT '0' COMMENT '订单金额：分',
  `order_status` int NOT NULL DEFAULT '0' COMMENT '订单状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';