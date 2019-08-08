create table if not exists t_user
(
	id varchar(64) not null comment '主键'
		primary key,
	username varchar(200) null comment '用户名',
	password varchar(255) null comment '密码',
	id_card varchar(255) null comment '身份证号',
	birthday date null comment '生日',
	sex int(4) null comment '性别',
	status varchar(4) null comment '状态',
	create_by varchar(64) null comment '创建人',
	create_date timestamp null comment '创建时间',
	update_by varchar(64) null comment '更新人',
	update_date timestamp null comment '更新时间',
	del_flag varchar(4) null comment '删除标记'
);



create table if not exists t_role
(
    id varchar(64) not null comment '主键'
        primary key,
    role_name varchar(200) null comment '角色名',
    role_code varchar(255) null comment '角色编码',
    status varchar(4) null comment '状态',
    create_by varchar(64) null comment '创建人',
    create_date timestamp null comment '创建时间',
    update_by varchar(64) null comment '更新人',
    update_date timestamp null comment '更新时间',
    del_flag varchar(4) null comment '删除标记'
);



create table if not exists t_user_role
(
    id varchar(64) not null comment '主键'
        primary key,
    user_id varchar(200) null comment '用户主键',
    role_id varchar(255) null comment '角色主键',
    create_by varchar(64) null comment '创建人',
    create_date timestamp null comment '创建时间',
    update_by varchar(64) null comment '更新人',
    update_date timestamp null comment '更新时间',
    del_flag varchar(4) null comment '删除标记'
);

