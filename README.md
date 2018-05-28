# spring-boot 示例项目
## 说明
本示例是一个普通的`spring-boot-web`项目，集成了`Swagger2`，`QueryDsl`，`MyBatis`以及`spring-boot-web`中自带的`Hibernate`。详情请查看项目中的注解。启动项目后，在 [Swagger-UI](http://localhost:8000/promise-app-server/swagger-ui.html) 中查看接口。

## 注意
1. 项目需要安装`lombok`插件！！！
2. 创建数据库的`.sql`文件在`/files`文件夹下，请自行创建数据库后并查看`/src/main/resources/application.yml`中数据源地址是否需要修改后再进行测试
3. `resources`下带`-dev`的`.yml`文件表示测试环境下的配置；带`-prod`表示生产环境下的配置
3. `MyBatis`相关的接口类在`me.frank.demo.mapper`包内创建
4. `MyBatis`相关的`XML`文件在`resources`下的`me/frank/demo/mapper`文件夹下创建
5. 需要在`Swagger-UI`中显示的`Controller`类需要标注`@Api`，具体使用方法参考`BaseController`
6. 需要简化`POJO`类的显示，需要标注`@Data`，具体使用方法参考`AppResponse`
8. 双向引用的实体类需要在`@ToString`和`@EqualsAndHashCode`里排除引用的类对象，以免造成系统错误，具体使用方法参考`AppUser`
9. 代码尽量符合阿里`Java`代码规约，`Eclipse`和`IntelliJ`都有相应的插件可以安装

## 启动步骤：
1. 执行`maven`命令生成实体类映射类：`mvn clean generate-resources`
2. 执行`maven`命令运行项目：`mvn spring-boot:run`

## 配置

1. 系统相关的参数配置在`application.properties`文件中，相应的配置都已有注释描述
2. 修改项目名的时候注意修改`server.context-path`参数
3. 修改包名的时候注意修改`mybatis.type-aliases-package`, `mybatis.mapper-locations`参数和`MethodLogger`中的包名
4. 修改数据库源的时候注意修改`spring.datasource`, `hikari.datasource`相应的参数
5. `spring.datasource`, `hikari.datasource`的部分参数需要保持一致，具体参考现有的配置

## 项目结构

**注：以下包名的前缀统一为`me.frank.demo`**

1. `aspect`包中存放切面操作相关类`MethodLogger`类会在`Controller`和`Service`方法执行前在控制台中输出方法名
2. `config`包中存放项目的配置类
3. `controller`包中存放项目的控制层类
4. `dto`包中存放数据传输对象
5. `entity`包中存放实体类，对应数据库中的表
6. `enums`包中存放枚举类，一般和实体类的状态属性相关
7. `exception`包中存放异常类，注意处理请求时，有业务异常的，抛出的`ServiceException`会被自动捕获，并返回相应的错误信息给前端。有需要抛出的业务异常在`ServiceException`中添加
8. `mapper`包中存放`MyBatis`对数据库表操作接口，不需要实现，注意方法名和相应的`*Mapper.xml`文件里的操作id保持一致
9. `properties`包中存放项目配置类，包含`*.yml`文件相对应的类以及一些常量类
10. `query`包中存放更复杂的`QueryDsl`查询接口
11. `repo`包中存放`JPA`的基本查询接口，需要继承同包下的`Repository`接口，会根据方法名自动生成查询`SQL`，不需要实现接口
12. `security`包中存放和权限、安全相关的`Filter`
13. `service`包中存放业务相关类
14. `util`包中存放工具类

## Git Commit 规范

Git `commit`的时候，按照以下消息规范的范围进行`commit`。最好不要出现一次`commit`同时出现多个范围的修改，如：一次`commit`中新增了接口，同时又修改了配置。

## Git Commit 消息规范

1. 用`A 新增`作为前缀表示**新增功能**，如`A 新增 消息接口`
2. 用`C 配置`作为前缀表示**系统配置修改**，如`C 配置 修改数据库连接地址`
3. 用`D 文档`作为前缀表示**文档相关修改**，如`D 文档 完善README`
4. 用`F 修复`作为前缀表示**修复bug**，如`F 修复 消息接口逻辑错误`
5. 用`M 其他`作为前缀表示**其他无法归类的修改**，如`M 其他 删除不必要的文件`
6. 用`O 优化`作为前缀表示**代码优化**，如`O 优化 消息接口逻辑优化`
7. 用`R 重构`作为前缀表示**代码重构**，如`R 重构 消息接口代码重构`
8. 用`S 格式`作为前缀表示**代码格式修改**，如`S 格式 消息接口代码格式修改`

## 前后端协作建议

建议使用[石墨](https://shimo.im/)协作