-- 创建数据库
CREATE DATABASE `springboot` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 创建教师表
create table teacher(
	id bigint(20) not null comment '主键id',
	name varchar(50) default null comment '姓名',
	subject tinyint(4) default null comment '教学科目（1：语文 2：数学 3：英语）',
	primary key (`id`)
) comment = '教师表';

-- 创建学生表
create table student(
	id bigint(20) not null comment '主键id',
	name varchar(50) default null comment '姓名',
	birthdate date default null comment '出生日期',
	teacher_id bigint(20) default null comment '教师id',
	primary key (`id`)
) comment = '学生表';
