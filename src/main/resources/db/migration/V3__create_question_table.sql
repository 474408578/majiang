create table question
(
	id int auto_increment,
	title VARCHAR(50) not null,
	description TEXT not null,
	creator int not null,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag VARCHAR(256),
	gmt_create bigint,
	gmt_modified BIGINT,
	constraint question_pk
		primary key (id)
);

