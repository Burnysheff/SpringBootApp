DROP TABLE course_review;
DROP TABLE course_user;
DROP TABLE course_theme;
DROP TABLE theme;
DROP TABLE courses;
DROP TABLE users;
DROP TABLE reviews;

CREATE TABLE theme (
    theme_id bigint primary key,
    direct varchar(20) NOT NULL
);

INSERT INTO theme
VALUES (1, 'it'),
       (2, 'finance'),
       (3, 'qa');