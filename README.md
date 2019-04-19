# eureka-client-provider
**标准代码服务所在项目**
-----
- 此项目是`springboot`项目,也集成了`springCloud`,注册中心是`eureka`,因此运行此项目必须先运行`eureka-server`
- 由于**数据库**是连的我本地的,所以**数据库配置**请也修改为你自己的数据库配置
- 目前就一张表,表名是`table_name`
-----
表结构是这样的

id|groudCode|groudName|dicCode|dicName
:-:|:-:|:-:|:-:|:-:
8f3e1c3642ed4cd9a58e0c2b5d864d8d|color|颜色|0|红色|

建表语句:
```
  CREATE TABLE `table_name` (
  `id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '本条记录id',
  `groupCode` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '组代码:例如 "color"',
  `groupName` varchar(32) NOT NULL COMMENT '组名',
  `dicCode` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '字典代码:例如''1'',''2'',''A''',
  `dicName` varchar(100) NOT NULL COMMENT '字典代码代表的意思',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典demo表';
```
