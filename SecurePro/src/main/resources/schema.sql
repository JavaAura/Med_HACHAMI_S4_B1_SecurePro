CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY 'root';
   
    CREATE TABLE IF NOT EXISTS users (
        username varchar(255) NOT NULL PRIMARY KEY,
        password varchar(455) NOT NULL,
        enabled BOOLEAN NOT NULL
    );

    -- Create the authorities table if it does not exist
    CREATE TABLE IF NOT EXISTS authorities (
        username varchar(255) NOT NULL,
        authority varchar(255) NOT NULL,
        CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
    );


    create unique index IF NOT EXISTS ix_auth_username on authorities (username,authority);