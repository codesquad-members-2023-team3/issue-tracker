show databases ;
use issue_tracker_database;

desc USER;
insert into USER(id, password, nickname, img_url) values ('1234', 'codesquad', 'BE', 'https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg');
select * from USER;


desc MILESTONE;
insert into MILESTONE(name, scheduled_completion_date, description_for_label)
values ('BE STEP1', '2023-05-20 00:00:00', 'BE 1주차 이슈들');
select * from MILESTONE;

desc ISSUE;
insert into ISSUE(contents, state, created_date, title, user_id, milestone_name)
values ('첫 번째 이슈 내용', true, '2023-5-15 19:37:47', '제목 1', 1234, 'BE STEP1'), ('두 번째 이슈 내용', false, '2023-5-15 19:37:47','제목 2', 1234, 'BE STEP1'), ('세 번째 이슈 내용', true,'2023-5-15 19:37:47', '제목 3', 1234, 'BE STEP1');
select * from ISSUE;

desc LABEL;
insert into LABEL(name, description, background_color, text_color)
values ('feature', '기능을 만들었슴둥', '#000000', '#004DE3'),
       ('fix', '버그를 고쳤음', '#123456', '#654321');
select * from LABEL;

desc ISSUE_LABEL_RELATION;
insert into ISSUE_LABEL_RELATION(issue_number, label_name)
values (1, 'feature'), (1, 'fix'), (2, 'fix');
select * from ISSUE_LABEL_RELATION;

