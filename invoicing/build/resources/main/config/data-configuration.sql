CREATE DATABASE invoicing;
GRANT ALL
    ON invoicing.* TO 'kdg_user'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'kdg_user'@'%';
FLUSH
    PRIVILEGES;