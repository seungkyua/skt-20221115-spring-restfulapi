insert into user values(90001, sysdate(), 'User1', 'test111', '701010-1111111');
insert into user values(90002, sysdate(), 'User2', 'test222', '801111-2222222');
insert into user values(90003, sysdate(), 'User3', 'test333', '901212-1111111');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);