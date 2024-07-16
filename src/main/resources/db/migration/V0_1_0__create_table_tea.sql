CREATE TABLE "tea"
(
    id bigserial,
    name varchar(40),
    description varchar(255),
    price bigserial,
    country varchar(255),
    CONSTRAINT pr_id PRIMARY KEY (id)
)