DROP DATABASE IF EXISTS dpconsole;
DELETE FROM mysql.user WHERE User='dpconsole';
FLUSH PRIVILEGES;
CREATE DATABASE IF NOT EXISTS dpconsole CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON dpconsole.* to `dpconsole`@`127.0.0.1` IDENTIFIED BY 'dpconsole' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON dpconsole.* to `dpconsole`@`localhost` IDENTIFIED BY 'dpconsole' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON dpconsole.* to `dpconsole`@`%` IDENTIFIED BY 'dpconsole' WITH GRANT OPTION;
USE dpconsole;