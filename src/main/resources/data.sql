insert into member (id, password, nickname, name, phone, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'jiwon','01024476990', 1);
insert into member (id, password, nickname, name, phone, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user','sumin','01044445555', 1);

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into member_authority (member_key, authority_name) values (1, 'ROLE_USER');
insert into member_authority (member_key, authority_name) values (1, 'ROLE_ADMIN');
insert into member_authority (member_key, authority_name) values (2, 'ROLE_USER');