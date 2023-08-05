
drop table if exists locations CASCADE;
drop table if exists events CASCADE;
drop table if exists categories CASCADE;
drop table if exists users CASCADE;
drop table if exists requests CASCADE;
drop table if  exists compilations CASCADE;
drop table if  exists  compilation_event CASCADE;
drop table if  exists  comments CASCADE;

create table if not exists users
(
    id integer generated by default as identity constraint users_pk primary key,
    email varchar(254) not null
        unique,
    name  varchar(250) not null
        unique
);

create table if not exists categories
(
    id   integer generated always as identity
        constraint categories_pk
            primary key,
    name varchar(50) not null
        constraint categories_pk2
            unique
);

create table if not exists public.locations
(
    id  integer generated always as identity
        constraint locations_pk
            primary key,
    lat double precision not null,
    lon double precision not null
);

create table if not exists public.events
(
    id                 integer generated always as identity
        constraint events_pk
            primary key,
    annotation         varchar(2000)         not null,
    category_id        integer               not null
        constraint events_categories_id_fk
            references public.categories
            on delete restrict,
    description        varchar(7000)         not null,
    event_date         timestamp             not null,
    location_id        integer               not null
        constraint events_locations_id_fk
            references public.locations
            on update cascade on delete cascade,
    paid               boolean default false not null,
    participant_limit  integer default 0,
    request_moderation boolean default true  not null,
    title              varchar(120)          not null,
    initiator_id       integer               not null
        constraint events_users_id_fk
            references public.users
            on delete cascade,
    created_on         timestamp             not null,
    published_on       timestamp,
    state              varchar(50),
    confirmed_requests integer default 0,
    views              integer default 0
);

create table if not exists public.requests
(
    event_id  integer not null constraint requests_events_id_fk references public.events on update cascade on delete cascade,
    requester_id integer not null constraint requests_users_id_fk references public.users on update cascade on delete cascade,
    status varchar(50) not null,
    created timestamp   not null,
    id integer generated by default as identity constraint requests_pk primary key
);

create table if not exists public.compilations
(
    id integer generated by default as identity constraint compilations_pk primary key,
    pinned boolean default false,
    title  varchar(50) not null
);

CREATE TABLE if not exists compilation_event(
   compilation_id bigint REFERENCES compilations(id),
   event_id bigint REFERENCES events(id),
   PRIMARY KEY(compilation_id, event_id)
);

create table if not exists public.comments
(
    id integer generated by default as identity constraint comments_pk primary key,
    text varchar(700) not null,
    author_id integer not null constraint comments_users_id_fk references public.users on update cascade on delete cascade,
    created   timestamp not null,
    event_id  integer not null constraint comments_events_id_fk references public.events on update cascade on delete cascade
);