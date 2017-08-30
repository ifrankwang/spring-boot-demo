# Spring-boot 示例项目

**注：**
1. 创建数据库的`.sql`文件在`/src/main/resources/static`下，请自行创建数据库后并查看`/src/main/resources/application.properties`中数据源地址是否需要修改后再进行测试。
2. 启动项目前需要执行`Maven`命令：`mvn generate-sources`

本示例是一个普通的`spring-boot-web`项目，集成了`Swagger2`，`QueryDsl`，`MyBatis`以及`spring-boot-web`中自带的`Hibernate`。详情请查看项目中的注解。
