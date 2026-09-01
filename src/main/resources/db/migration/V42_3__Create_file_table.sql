create table if not exists file
(
    id varchar
    constraint file_pk primary key,
    title varchar not null,
    email varchar not null,
    created_at timestamp not null
);