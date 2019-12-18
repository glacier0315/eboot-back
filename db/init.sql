create table sys_user (
		id varchar ( 64 ) not null comment '主键' primary key,
		username varchar ( 200 ) comment '用户名',
		password varchar ( 255 ) comment '密码',
		nickname varchar ( 200 ) comment '昵称',
		salt varchar ( 255 ) comment '盐值',
		id_card varchar ( 255 ) comment '身份证号',
		birthday date null comment '生日',
		sex int ( 4 ) comment '性别',
		email varchar ( 255 ) comment '邮箱',
		mobile varchar ( 255 ) comment '手机号',
		dept_id varchar ( 255 ) comment '单位',
		status varchar ( 4 ) comment '状态',
		create_by varchar ( 64 ) comment '创建人',
		create_date timestamp null comment '创建时间',
		update_by varchar ( 64 ) comment '更新人',
		update_date timestamp null comment '更新时间',
		del_flag varchar ( 4 ) comment '删除标记'
	) comment '用户表';
create index sys_user_1 on sys_user ( username );
create index sys_user_2 on sys_user ( id_card );
create index sys_user_3 on sys_user ( status );


create table sys_role (
	id varchar ( 64 ) not null comment '主键' primary key,
	name varchar ( 200 ) comment '角色名',
	code varchar ( 255 ) comment '角色编码',
	status varchar ( 4 ) comment '状态',
	create_by varchar ( 64 ) comment '创建人',
	create_date timestamp null comment '创建时间',
	update_by varchar ( 64 ) comment '更新人',
	update_date timestamp null comment '更新时间',
	del_flag varchar ( 4 ) comment '删除标记'
) comment '角色表';
create index sys_role_1 on sys_role ( code );
create index sys_role_2 on sys_role ( status );


create table sys_user_role (
	id varchar ( 64 ) not null comment '主键' primary key,
	user_id varchar ( 64 ) comment '用户主键',
	role_id varchar ( 64 ) comment '角色主键'
) comment '用户角色关联表';
create index sys_user_role_1 on sys_user_role ( user_id );
create index sys_user_role_2 on sys_user_role ( role_id );


create table sys_menu (
	id varchar ( 64 ) not null comment '主键' primary key,
	name varchar ( 200 ) comment '名称',
	url varchar ( 255 ) comment 'url',
	perms varchar ( 255 ) comment '权限',
	type int ( 4 ) comment '类型',
	status int ( 4 ) comment '状态',
	open int ( 4 ) comment '打开方式' default 1,
	order_num int ( 4 ) comment '排序号',
	icon varchar ( 255 ) comment '图标',
	parent_id varchar ( 255 ) comment '父级id',
	create_by varchar ( 64 ) comment '创建人',
	create_date timestamp null comment '创建时间',
	update_by varchar ( 64 ) comment '更新人',
	update_date timestamp null comment '更新时间',
	del_flag varchar ( 4 ) comment '删除标记'
) comment '菜单表';
create index sys_menu_1 on sys_menu ( parent_id );
create index sys_menu_2 on sys_menu ( type );
create index sys_menu_3 on sys_menu ( status );


create table sys_role_menu (
	id varchar ( 64 ) not null comment '主键' primary key,
	role_id varchar ( 255 ) comment '角色主键',
	menu_id varchar ( 255 ) comment '菜单主键'
) comment '角色菜单关联表';
create index sys_role_menu_1 on sys_role_menu ( menu_id );
create index sys_role_menu_2 on sys_role_menu ( role_id );


create table sys_dept (
	id varchar ( 64 ) not null comment '主键' primary key,
	name varchar ( 200 ) comment '名称',
	code varchar ( 255 ) comment '编码',
	type int ( 4 ) comment '类型',
	status int ( 4 ) comment '状态',
	order_num int ( 4 ) comment '排序号',
	level int ( 4 ) comment '层级',
	parent_id varchar ( 255 ) comment '父级id',
	create_by varchar ( 64 ) comment '创建人',
	create_date timestamp null comment '创建时间',
	update_by varchar ( 64 ) comment '更新人',
	update_date timestamp null comment '更新时间',
	del_flag varchar ( 4 ) comment '删除标记'
) comment '组织机构表';
create index sys_dept_1 on sys_dept ( parent_id );
create index sys_dept_2 on sys_dept ( type );
create index sys_dept_3 on sys_dept ( status );
create index sys_dept_4 on sys_dept ( code );



create table sys_role_dept (
	id varchar ( 64 ) not null comment '主键' primary key,
	role_id varchar ( 255 ) comment '角色主键',
	dept_id varchar ( 255 ) comment '组织机构主键'
) comment '角色和组织机构关联表';
create index sys_role_dept_1 on sys_role_dept ( dept_id );
create index sys_role_dept_2 on sys_role_dept ( role_id );



create table sys_config (
	id varchar ( 64 ) not null comment '主键' primary key,
	value varchar ( 255 ) comment '值',
	label varchar ( 255 ) comment '名称',
	type varchar ( 255 ) comment '类型',
	description varchar ( 500 ) comment '描述',
	order_num int ( 4 ) comment '排序',
	remarks varchar ( 500 ) comment '备注',
	create_by varchar ( 64 ) comment '创建人',
	create_date timestamp null comment '创建时间',
	update_by varchar ( 64 ) comment '更新人',
	update_date timestamp null comment '更新时间',
	del_flag varchar ( 4 ) comment '删除标记'
) comment '设置表';
create index sys_config_1 on sys_config ( type );
create index sys_config_2 on sys_config ( value );


create table sys_dict (
	id varchar ( 64 ) not null comment '主键' primary key,
	code varchar ( 50 ) comment '值',
	name varchar ( 50 ) comment '名称',
	type varchar ( 255 ) comment '类型',
	parent_id varchar ( 255 ) comment '父级id',
	description varchar ( 500 ) comment '描述',
	order_num int ( 4 ) comment '排序',
	remarks varchar ( 500 ) comment '备注',
	create_by varchar ( 64 ) comment '创建人',
	create_date timestamp null comment '创建时间',
	update_by varchar ( 64 ) comment '更新人',
	update_date timestamp null comment '更新时间',
	del_flag varchar ( 4 ) comment '删除标记'
) comment '字典表';
create index sys_dict_1 on sys_dict ( type );
create index sys_dict_2 on sys_dict ( code );
create index sys_dict_3 on sys_dict ( parent_id );

create table sys_log (
	id varchar ( 64 ) not null comment '主键' primary key,
	user_id varchar ( 64 ) comment '登录id',
	url varchar ( 255 ) comment 'url',
	ip varchar ( 255 ) comment '登录ip',
	user_agent varchar ( 255 ) comment '登录客户端',
    use_time int ( 10 ) comment '耗时微秒'
) comment '操作日志表';
create index sys_log_1 on sys_log ( user_id );


create table sys_login_log (
	id varchar ( 64 ) not null comment '主键' primary key,
	user_id varchar ( 64 ) comment '登录id',
	ip varchar ( 255 ) comment '登录ip',
	user_agent varchar ( 255 ) comment '登录客户端'
) comment '登录日志表';
create index sys_login_log_1 on sys_login_log ( user_id );


