drop table if exists mybatisgeneratorpoc.todo cascade;
drop schema if exists mybatisgeneratorpoc cascade;
create schema mybatisgeneratorpoc;
create table mybatisgeneratorpoc.todo (
  id bigserial not null
  , title TEXT NOT NULL
  , details TEXT
  , finished BOOLEAN NOT NULL
  , created_at timestamp(6) without time zone
  , updated_at timestamp(6) without time zone
  , updated_by bigint
  , update_cause character varying(32)
  , version bigint
);