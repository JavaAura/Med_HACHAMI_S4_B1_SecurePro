CREATE TABLE IF NOT EXISTS users (
    username CITEXT NOT NULL PRIMARY KEY,
    password CITEXT NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Create the authorities table if it does not exist
CREATE TABLE IF NOT EXISTS authorities (
    username CITEXT NOT NULL,
    authority CITEXT NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);


create unique index IF NOT EXISTS ix_auth_username on authorities (username,authority);