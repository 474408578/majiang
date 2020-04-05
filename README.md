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

关于获取githubUser对象时有的时候会报错 "connection reset"
[【Github登录】更新官方推荐的使用access_token安全访问API的方式，使用Github推荐的最新方式（Authorization HTTP header），旧方式（query parameter）即将被废弃](https://niter.cn/p/115)

[IDEA + devtools实现热部署](https://blog.csdn.net/qq_29918079/article/details/100580274)


[windows端口被占用解决方法](https://blog.csdn.net/zt15732625878/article/details/80904437?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1)

[Mybatis不支持方法的重载](https://www.cnblogs.com/guanghe/p/10636188.html)


[解决github图片不显示和加载速度慢的问题](https://niter.cn/p/125)

[Mybatis自增长id处理](https://www.cnblogs.com/hongdada/p/9956992.html)


