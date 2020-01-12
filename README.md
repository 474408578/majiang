跟着码匠笔记学习Spring Boot课程的学习内容

### IDEA快捷键
```
CTRL + TAB   切换到上一次的窗口
```

### 报错及解决方法
#### 1、flyway报错
##### 报错信息
```
[ERROR] Failed to execute goal org.flywaydb:flyway-maven-plugin:5.2.4:migrate (default-cli) on project majinag: 
   org.flywaydb.core.api.FlywayException: Validate failed: 
   Detected resolved migration not applied to database: 4 -> [Help 1]

```
##### 解决方法：
```mvn -Dflyway.outOfOrder=true flyway:migrate```
#### 2、在IDEA使用lombok，可以安装一个Lombok插件

#### 3、 自己电脑的TCP/IP DNS更改
##### 报错信息
```网页可能暂时无法连接，或者它已永久性地移动到了新网址```
##### 解决方法
参考博客!(网页可能暂时无法连接)[https://www.jianshu.com/p/231ac5ddbfaf]

