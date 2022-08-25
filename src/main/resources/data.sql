CREATE TABLE theme (
    theme_id bigint primary key,
    direct varchar(20) NOT NULL
);

INSERT INTO theme
VALUES (1, 'it'),
       (2, 'finance'),
       (3, 'qa');