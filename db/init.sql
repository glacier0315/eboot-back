create table if not exists t_user
(
    id          varchar(64) not null comment '主键'
        primary key,
    username    varchar(200) comment '用户名',
    password    varchar(255) comment '密码',
    id_card     varchar(255) comment '身份证号',
    birthday    date        null comment '生日',
    sex         int(4) comment '性别',
    status      varchar(4) comment '状态',
    create_by   varchar(64) comment '创建人',
    create_date timestamp   null comment '创建时间',
    update_by   varchar(64) comment '更新人',
    update_date timestamp   null comment '更新时间',
    del_flag    varchar(4) comment '删除标记'
) comment '用户表';

create index t_user_1 on t_user (username);
create index t_user_2 on t_user (id_card);
create index t_user_3 on t_user (status);


create table t_role
(
    id          varchar(64) not null comment '主键'
        primary key,
    role_name   varchar(200) comment '角色名',
    role_code   varchar(255) comment '角色编码',
    status      varchar(4) comment '状态',
    create_by   varchar(64) comment '创建人',
    create_date timestamp   null comment '创建时间',
    update_by   varchar(64) comment '更新人',
    update_date timestamp   null comment '更新时间',
    del_flag    varchar(4) comment '删除标记'
) comment '角色表';
create index t_role_1 on t_role (role_code);
create index t_role_2 on t_role (status);


create table t_user_role
(
    id          varchar(64) not null comment '主键'
        primary key,
    user_id     varchar(200) comment '用户主键',
    role_id     varchar(255) comment '角色主键',
    create_by   varchar(64) comment '创建人',
    create_date timestamp   null comment '创建时间',
    update_by   varchar(64) comment '更新人',
    update_date timestamp   null comment '更新时间',
    del_flag    varchar(4) comment '删除标记'
) comment '用户角色关联表';
create index t_user_role_1 on t_user_role (user_id);
create index t_user_role_2 on t_user_role (role_id);



create table t_menu
(
    id          varchar(64) not null comment '主键'
        primary key,
    name        varchar(200) comment '名称',
    url         varchar(255) comment 'url',
    perms       varchar(255) comment '权限',
    type        int(4) comment '类型',
    status      int(4) comment '状态',
    order_num   int(4) comment '排序号',
    icon        varchar(255) comment '图标',
    parent_id   varchar(255) comment '父级id',
    create_by   varchar(64) comment '创建人',
    create_date timestamp   null comment '创建时间',
    update_by   varchar(64) comment '更新人',
    update_date timestamp   null comment '更新时间',
    del_flag    varchar(4) comment '删除标记'
) comment '菜单表';
create index t_menu_1 on t_menu (parent_id);
create index t_menu_2 on t_menu (type);
create index t_menu_3 on t_menu (status);


create table t_role_menu
(
    id          varchar(64) not null comment '主键'
        primary key,
    role_id     varchar(255) comment '角色主键',
    menu_id     varchar(255) comment '角色主键',
    create_by   varchar(64) comment '创建人',
    create_date timestamp   null comment '创建时间',
    update_by   varchar(64) comment '更新人',
    update_date timestamp   null comment '更新时间',
    del_flag    varchar(4) comment '删除标记'
) comment '角色菜单关联表';
create index t_role_menu_1 on t_role_menu (menu_id);
create index t_role_menu_2 on t_role_menu (role_id);
