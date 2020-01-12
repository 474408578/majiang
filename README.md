跟着码匠笔记学习Spring Boot课程的学习内容

### IDEA快捷键
```
CTRL + TAB   切换到上一次的窗口
```

### 报错及解决方法
#### 1 flyway报错
##### 报错信息
```
[ERROR] Failed to execute goal org.flywaydb:flyway-maven-plugin:5.2.4:migrate (default-cli) on project majinag: 
   org.flywaydb.core.api.FlywayException: Validate failed: 
   Detected resolved migration not applied to database: 4 -> [Help 1]

```
##### 解决方法：
```mvn -Dflyway.outOfOrder=true flyway:migrate```
#### 在IDEA使用lombok，可以安装一个Lombok插件