INSERT INTO business_member(email, password, name, company_name, created_at, expired_at)
VALUES ('test1@test.com', '1234', 'test1', 'company1', now(), null),
       ('test2@test.com', '1234', 'test2', 'company2', now(), null),
       ('test3@test.com', '1234', 'test3', 'company3', now(), null),
       ('test4@test.com', '1234', 'test4', 'company4', now(), null),
       ('test5@test.com', '1234', 'test5', 'company5', now(), null),
       ('test6@test.com', '1234', 'test6', 'company6', now(), null);

INSERT INTO article(price, business_member_id, created_at, updated_at, category, content, location, status, title )
VALUES (10000, 1, now(), null, 'category1', 'content', 'location1', 'status', 'title1'),
       (10000, 1, now(), null, 'category2', 'content', 'location2', 'status', 'title2'),
       (10000, 1, now(), null, 'category3', 'content', 'location3', 'status', 'title3'),
       (10000, 2, now(), null, 'category4', 'content', 'location4', 'status', 'title4'),
       (10000, 2, now(), null, 'category5', 'content', 'location5', 'status', 'title5'),
       (10000, 3, now(), null, 'category6', 'content', 'location6', 'status', 'title6'),
       (10000, 4, now(), null, 'category7', 'content', 'location7', 'status', 'title7'),
       (10000, 5, now(), null, 'category8', 'content', 'location8', 'status', 'title8')