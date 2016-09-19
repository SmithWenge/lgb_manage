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

### COURSE NUMUBER
```
CCDDMMYYBB(课程2位系2位专业2位年级2位班级2位) 前六位座位判断是否可报名课程
```

###考勤
```
http://localhost:8080/lgbmanage/student/card/routeIndex.action
```

### 成绩录入
```
http://localhost:8080/lgbmanage/teaScore/routerLogin.action
```