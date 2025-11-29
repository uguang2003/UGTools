create table if not exists t_msg_http
(
    id            integer
        constraint t_msg_http_pk
            primary key autoincrement,
    msg_name      text,
    method        text,
    url           text,
    params        text,
    headers       text,
    cookies       text,
    body          text,
    body_type     text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_msg_http_msg_name_uindex
    on t_msg_http (msg_name);

create table if not exists t_quick_note
(
    id            integer
        constraint t_quick_note_pk
            primary key autoincrement,
    name          text,
    content       text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_quick_note_name_uindex
    on t_quick_note (name);

create table if not exists t_json_beauty
(
    id            integer
        constraint t_json_beauty_pk
            primary key autoincrement,
    name          text,
    content       text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_json_beauty_name_uindex
    on t_json_beauty (name);

create table if not exists t_host
(
    id            integer
        constraint t_host_pk
            primary key autoincrement,
    name          text,
    content       text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_host_name_uindex
    on t_host (name);

create table if not exists t_qr_code
(
    id            integer
        constraint t_qr_code_pk
            primary key,
    content       text,
    create_time   datetime,
    modified_time datetime
);

create table if not exists t_favorite_color_list
(
    id            integer
        constraint t_favorite_color_list_pk
            primary key autoincrement,
    title         text,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_favorite_color_list_uindex
    on t_favorite_color_list (title);

create table if not exists t_favorite_color_item
(
    id            integer
        constraint t_favorite_color_item_pk
            primary key autoincrement,
    list_id       integer,
    name          text,
    value         text,
    sort_num      integer,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_favorite_color_item_uindex
    on t_favorite_color_item (list_id, name);

create table if not exists t_func_content
(
    id            integer
        constraint t_func_content_pk
            primary key autoincrement,
    func          text,
    content       text,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

-- text diff
-- 文档主表
CREATE TABLE IF NOT EXISTS doc (
        doc_id      INTEGER PRIMARY KEY AUTOINCREMENT,
        name        TEXT NOT NULL UNIQUE
);

-- 版本表
CREATE TABLE IF NOT EXISTS doc_revision (
        rev_id      INTEGER PRIMARY KEY AUTOINCREMENT,
        doc_id      INTEGER NOT NULL,
        created_at  DATETIME NOT NULL DEFAULT (datetime('now')),
        content     TEXT NOT NULL,
        FOREIGN KEY (doc_id) REFERENCES doc(doc_id)
);

-- 缓存行级 unified diff（从前一版本到本版本）
CREATE TABLE IF NOT EXISTS doc_diff_cache (
        from_rev_id INTEGER NOT NULL,
        to_rev_id   INTEGER NOT NULL,
        unified     TEXT NOT NULL,
        PRIMARY KEY (from_rev_id, to_rev_id),
        FOREIGN KEY (from_rev_id) REFERENCES doc_revision(rev_id),
        FOREIGN KEY (to_rev_id)   REFERENCES doc_revision(rev_id)
);

INSERT INTO t_quick_note (name, content, create_time, modified_time)
VALUES ('关于随手记', '随手记可以用来快速记录一些：
代码片段、常用的SQL、常用的接口、常用的数据、暂存一些临时log等

点击加号按钮，开始创建一条新的笔记', '2019-10-24 10:42:13', '2019-10-24 11:23:18');

INSERT INTO t_json_beauty (name, content, create_time, modified_time)
VALUES ('未命名', '{}', '2019-10-24 10:42:13', '2019-10-24 11:23:18');

INSERT INTO t_host (name, content, create_time, modified_time)
VALUES ('未命名', '', '2019-10-24 10:42:13','2019-10-24 11:23:18');


INSERT INTO t_favorite_color_list (id, title, remark, create_time, modified_time) VALUES (1, '默认收藏夹', null, '1574434141000', '1574434144000');


alter table t_quick_note add color text;

alter table t_quick_note add style text;

alter table t_quick_note add font_name text;

alter table t_quick_note add font_size text;

alter table t_quick_note add syntax text;


create table if not exists t_favorite_regex_list
(
    id            integer
    constraint t_favorite_regex_list_pk
    primary key autoincrement,
    title         text,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_favorite_regex_list_uindex
    on t_favorite_regex_list (title);

create table if not exists t_favorite_regex_item
(
    id            integer
    constraint t_favorite_regex_item_pk
    primary key autoincrement,
    list_id       integer,
    name          text,
    value         text,
    sort_num      integer,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_favorite_regex_item_uindex
    on t_favorite_regex_item (list_id, name);

INSERT INTO t_favorite_regex_list (id, title, remark, create_time, modified_time)
VALUES (1, '默认收藏夹', null, '1574434141000', '1574434144000');


create table if not exists t_favorite_cron_list
(
    id            integer
    constraint t_favorite_cron_list_pk
    primary key autoincrement,
    title         text,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_favorite_cron_list_uindex
    on t_favorite_cron_list (title);

create table if not exists t_favorite_cron_item
(
    id            integer
    constraint t_favorite_cron_item_pk
    primary key autoincrement,
    list_id       integer,
    name          text,
    value         text,
    sort_num      integer,
    remark        text,
    create_time   datetime,
    modified_time datetime
);

create unique index if not exists t_favorite_cron_item_uindex
    on t_favorite_cron_item (list_id, name);

INSERT INTO t_favorite_cron_list (id, title, remark, create_time, modified_time)
VALUES (1, '默认收藏夹', null, '1693894410000', '1693894410000');


alter table t_msg_http
    add response_body text;
alter table t_msg_http
    add response_headers text;
alter table t_msg_http
    add response_cookies text;

create table if not exists t_http_request_history
(
    id            integer
    constraint t_http_request_history_pk
    primary key autoincrement,
    request_id    integer,
    title         text,
    method        text,
    url           text,
    params        text,
    headers       text,
    cookies       text,
    body          text,
    body_type     text,
    response_body text,
    response_headers text,
    response_cookies text,
    status        text,
    cost_time     integer,
    create_time   datetime,
    modified_time datetime
);



alter table t_quick_note
    add line_wrap text;
