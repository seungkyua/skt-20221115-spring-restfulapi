insert into user(id, join_date, name, password, ssn) values(90001, now(), 'User1', 'test111', '701010-1111111');
insert into user(id, join_date, name, password, ssn) values(90002, now(), 'User2', 'test222', '801111-2222222');
insert into user(join_date, name, password, ssn) values(now(), 'User3', 'test333', '901212-3333333');
insert into user(join_date, name, password, ssn) values(now(), 'User4', 'test444', '901212-4444444');

insert into post(description, user_id) values('My first post', 90001);
insert into post(description, user_id) values('My second post', 90001);