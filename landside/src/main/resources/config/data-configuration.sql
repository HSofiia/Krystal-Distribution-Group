CREATE DATABASE landside;
GRANT ALL
    ON landside.* TO 'kdg_user'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'kdg_user'@'%';
FLUSH
    PRIVILEGES;