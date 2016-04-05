#Manual

### MySQL Config
```
CREATE SCHEMA `lgb_manage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'lgbmanager'@'localhost' IDENTIFIED BY 'lgbmanager';
GRANT ALL PRIVILEGES ON lgb_manage.* TO 'lgbmanager'@'localhost' WITH GRANT OPTION;
```

### URL
```
HOME: http://localhost:8080/lgbmanage/admin/routeLogin.action
LOG: http://localhost:8080/lgbmanage/admin/log/routePage.action
```