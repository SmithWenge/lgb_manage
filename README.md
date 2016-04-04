#Manual

### MySQL Config
```
CREATE SCHEMA `lgb_manage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'lgbmanager'@'localhost' IDENTIFIED BY 'lgbmanager';
GRANT ALL PRIVILEGES ON lgb_manage.* TO 'lgbmanager'@'localhost' WITH GRANT OPTION;
```