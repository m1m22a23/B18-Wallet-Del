CREATE TABLE users (
    user_id IDENTITY,
    user CHAR NOT NULL
);
CREATE TABLE income (
    id IDENTITY,
    user_id INT NOT NULL REFERENCES users(user_id),
    date CHAR NOT NULL,
    money INT NOT NULL
);
CREATE TABLE spend (
    id IDENTITY,
    user_id INT NOT NULL REFERENCES users(user_id),
    date CHAR NOT NULL,
    money INT NOT NULL
);
