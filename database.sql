CREATE
DATABASE projectk2;

create table role
(
    id        int auto_increment not null,
    name      varchar(20) not null,
    flag      varchar(10) default '0',
    create_at datetime,
    update_at datetime,

    primary key (id)
);

-- thong tin cac chi nhanh trong cong ty
create table branch
(
    id        int auto_increment not null,
    name      varchar(30) not null,
    address   varchar(50) not null,
    hotline   varchar(15) not null,
    email     varchar(20) not null,
    flag      varchar(10) default '0',
    create_at datetime,
    update_at datetime,

    primary key (id)
);

-- cac chuc vu trong cong ty
create table positions
(
    id        int auto_increment not null,
    name      varchar(20) not null,
    flag      varchar(10) default '0',
    create_at datetime,
    update_at datetime,

    primary key (id)
);

-- thong tin nhan vien trong cong ty
create table employee
(
    id           int auto_increment not null,
    f_name       varchar(20) not null,
    m_name       varchar(20),
    l_name       varchar(20) not null,
    dob          date        not null,
    address      varchar(50) not null,
    person_id    varchar(20) not null,
    phone        varchar(15) not null,
    email        varchar(20),
    positions_id int,
    flag         varchar(10) default '0',
    create_at    datetime,
    update_at    datetime,

    primary key (id),
    foreign key (positions_id) references positions (id)
);

-- tai khoan dang nhap vao ung dung
create table users
(
    id              int auto_increment not null,
    user_name       varchar(20) not null,
    password        varchar(25) not null,
    role_id         int         not null,
    employee_id     int         not null,
    employee_create int         not null,
    flag            varchar(10) default '0',
    create_at       datetime,
    update_at       datetime,

    primary key (id),
    foreign key (employee_id) references employee (id),
    foreign key (role_id) references role (id)
);

create table type_car
(
    id                  int auto_increment not null,
    name                varchar(30) not null,
    brand               varchar(30) not null,
    number_of_seats     int         not null,
    year_of_manufacture int,
    flag                varchar(10) default '0',
    create_at           datetime,
    update_at           datetime,

    primary key (id)
);

create table list_car
(
    id             int auto_increment not null,
    license_plates varchar(15) not null,
    type_car_id    int         not null,
    date_buy       date,
    description    varchar(200),
    flag           varchar(10) default '0',
    create_at      datetime,
    update_at      datetime,

    primary key (id),
    foreign key (type_car_id) references type_car (id)
);


create table router_list
(
    id          int auto_increment not null,
    code        varchar(20) not null,
    start_point int not null,
    destination int not null,
    start_time  time,
    end_time    time,
    price       float       not null,
    flag        varchar(10) default '0',
    create_at   datetime,
    update_at   datetime,

    primary key (id),
    foreign key (start_point) references branch (id),
    foreign key (destination) references branch (id)
);

create table task_list
(
    id             int auto_increment not null,
    code           varchar(20) not null ,
    list_car_id    int  not null,
    router_list_id int  not null,
    user_id        int  not null, -- tai khoan tai xe
    date_apply     date not null,
    status         varchar(10) default 'active' ,  -- 'active' , 'done' , 'cancel'
    flag           varchar(10) default '0',
    create_at      datetime,
    update_at      datetime,

    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (router_list_id) references router_list (id),
    foreign key (list_car_id) references list_car (id)
);

create table ticket_status(
    id int auto_increment not null,
    name varchar(20) not null ,
    description varchar(100) not null ,
    flag           varchar(10) default '0',
    create_at      datetime,
    update_at      datetime,

    primary key (id)
);

create table ticket
(
    id             int auto_increment not null,
    code           varchar(20) not null,
    customer_name  varchar(30) not null,
    customer_phone varchar(15) not null,
    branch_id      int         not null,          -- chi nhanh khach hang dat mua ve
    task_list_id   int         not null,          -- thong tin tuyen duong, xe , tai xe
    employee_id    int         not null,          -- thong tin nhan vien ban ve
    status         int         not null,          -- pending , confirm,  done
    flag           varchar(10) default '0',
    create_at      datetime,
    update_at      datetime,

    primary key (id),
    foreign key (task_list_id) references task_list (id),
    foreign key (branch_id) references branch (id),
    foreign key (employee_id) references employee (id),
    foreign key (status) references ticket_status(id)
);

create table report_day
(
    id                int auto_increment not null,
    code              varchar(20) not null,
    total_ticket      int         not null,
    total_ticket_sell int         not null

);




























