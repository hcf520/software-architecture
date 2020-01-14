-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.28 - MySQL Community Server (GPL)
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for db1
DROP DATABASE IF EXISTS `db1`;
CREATE DATABASE IF NOT EXISTS `db1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `db1`;

-- Dumping structure for function db1.getChildLst
DROP FUNCTION IF EXISTS `getChildLst`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildLst`(
	`rootId` INT
) RETURNS varchar(1000) CHARSET utf8mb4 COLLATE utf8mb4_bin
BEGIN DECLARE sTemp VARCHAR(1000); DECLARE sTempChd VARCHAR(1000); SET sTemp = '$'; SET sTempChd = CAST(rootId AS CHAR); WHILE sTempChd IS NOT NULL DO SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(permissionsId) INTO sTempChd
FROM sys_permissions
WHERE FIND_IN_SET(parent_permissionId,sTempChd)>0; END WHILE; RETURN sTemp; 
END//
DELIMITER ;

-- Dumping structure for table db1.sys_permissions
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE IF NOT EXISTS `sys_permissions` (
  `permissionsId` int(11) NOT NULL AUTO_INCREMENT,
  `permissions_name` varchar(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '权限名称',
  `control_area` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '权限控制区域',
  `URI` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源路径',
  `parent_permissionId` int(11) DEFAULT NULL COMMENT '所属父级权限',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0：正常；1：失效',
  `description` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`permissionsId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Dumping data for table db1.sys_permissions: ~4 rows (大约)
/*!40000 ALTER TABLE `sys_permissions` DISABLE KEYS */;
INSERT INTO `sys_permissions` (`permissionsId`, `permissions_name`, `control_area`, `URI`, `parent_permissionId`, `status`, `description`) VALUES
	(1, '系统根权限', 'system', NULL, NULL, 0, '平台系统权限来源'),
	(2, '数据报表', 'dataTable', NULL, 1, 0, '报表模块权限来源'),
	(3, '二级模块1', 'twoModel1', NULL, 1, 0, '二级模块权限来源'),
	(4, '三级模块1', 'threeModel1', NULL, 3, 0, '三级模块权限来源');
/*!40000 ALTER TABLE `sys_permissions` ENABLE KEYS */;

-- Dumping structure for table db1.sys_role
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `description` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '释义说明',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0：正常；1：禁用',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

-- Dumping data for table db1.sys_role: ~3 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`roleId`, `role_name`, `description`, `status`) VALUES
	(1, '系统管理员', '系统最高权限', 0),
	(2, '报表用户', '报表模块使用权限', 0),
	(3, '二级模块1', '二级模块权限', 0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- Dumping structure for table db1.sys_role_permissions
DROP TABLE IF EXISTS `sys_role_permissions`;
CREATE TABLE IF NOT EXISTS `sys_role_permissions` (
  `roleId` int(11) DEFAULT NULL,
  `permissionsId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Dumping data for table db1.sys_role_permissions: ~3 rows (大约)
/*!40000 ALTER TABLE `sys_role_permissions` DISABLE KEYS */;
INSERT INTO `sys_role_permissions` (`roleId`, `permissionsId`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `sys_role_permissions` ENABLE KEYS */;

-- Dumping structure for table db1.sys_user
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '用户ID账号',
  `userpwd` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
  `username` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `email` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户电子邮箱',
  `phone` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户手机号',
  `roleId` int(11) NOT NULL COMMENT '系统角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- Dumping data for table db1.sys_user: ~3 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `userid`, `userpwd`, `username`, `email`, `phone`, `roleId`) VALUES
	(1, 'hechuanfei', '12345678', '贺传飞', 'chuanfei.he', '18483694058', 1),
	(6, 'test01', '65964d15c22b81872714d14466ce70c4c25397316d43c189', '测试', NULL, NULL, 3),
	(7, 'test02', 'f70f2e155f62947c3a31e568726a3608344a58368ae5195b', '测试', NULL, NULL, 2);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
