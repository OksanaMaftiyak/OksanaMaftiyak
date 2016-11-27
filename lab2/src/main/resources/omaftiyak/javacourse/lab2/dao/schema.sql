create table libraries (
	id   bigserial   not null primary key,
	name varchar(32) not null
);

create table books (
  id              bigserial               not null primary key,
  libraryId       bigint                  not null,
  booktitle       character varying(32)   not null,
  author          character varying(32)   not null,
  yearpublication smallint                not null,
  genre           character varying(32)   not null,
  description     character varying(1024) not null,
  language        character varying(32)   not null,
  foreign key (libraryId) references libraries(id)
);
