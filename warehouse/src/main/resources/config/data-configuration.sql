CREATE DATABASE whside;
GRANT ALL
    ON whside.* TO 'kdg_user'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'kdg_user'@'%';
FLUSH
    PRIVILEGES;