# spring-boot 示例项目
## 说明
本示例是一个普通的`spring-boot-web`项目，集成了`Swagger2`，`QueryDsl`，`MyBatis`，`Hibernate`和`Spring Security`。详情请查看项目中的注解。启动项目后，在 [Swagger-UI](http://localhost:80/spring-boot-demo/swagger-ui.html) 中查看接口。

## 注意
1. 项目需要安装`lombok`插件！！！
2. 创建数据库的`.sql`文件在`/files`文件夹下，请自行创建数据库后并查看`/src/main/resources/application.yml`中数据源地址是否需要修改后再进行测试
3. `resources`下带`-dev`的`.yml`文件表示测试环境下的配置；带`-prod`表示生产环境下的配置
3. `MyBatis`相关的接口类在`com.github.ifrankwang.spring.mapper`包内创建
4. `MyBatis`相关的`XML`文件在`resources`下的`com/github/ifrankwang/spring/mapper`文件夹下创建
5. 需要在`Swagger-UI`中显示的`Controller`类需要标注`@Api`
6. 需要简化`POJO`类的显示，需要标注`@Data`，具体使用方法参考`AppResponse`
8. 双向引用的实体类需要在`@ToString`和`@EqualsAndHashCode`里排除引用的类对象，以免造成系统错误
9. 代码尽量符合阿里`Java`代码规约，`Eclipse`和`IntelliJ`都有相应的插件可以安装

## 启动步骤：
1. 执行`maven`命令生成实体类映射类：`mvn clean generate-resources`
2. 执行`maven`命令运行项目：`mvn spring-boot:run`

## 配置

1. 系统相关的参数配置在`src/main/resource/application.yml`文件中，相应的配置都已有注释描述
2. 修改项目名的时候注意修改`server.servlet.context-path`参数
3. 修改包名的时候注意修改`mybatis.type-aliases-package`, `mybatis.mapper-locations`参数和`MethodLogger`中的包名
4. 修改数据库源的时候注意修改`hikari.datasource`相应的参数

## 项目结构

**注：以下包名的前缀统一为`com.github.ifrankwang.spring`**

TODO 之后做补充

## 项目规范

### Git Commit 消息

1. 用`A 新增`作为前缀表示**新增功能**，如`A 新增 消息接口`
2. 用`C 配置`作为前缀表示**系统配置修改**，如`C 配置 修改数据库连接地址`
3. 用`D 文档`作为前缀表示**文档相关修改**，如`D 文档 完善README`
4. 用`F 修复`作为前缀表示**修复bug**，如`F 修复 消息接口逻辑错误`
5. 用`M 其他`作为前缀表示**其他无法归类的修改**，如`M 其他 删除不必要的文件`
6. 用`O 优化`作为前缀表示**代码优化**，如`O 优化 消息接口优化`
7. 用`R 重构`作为前缀表示**代码重构**，如`R 重构 消息接口代码重构`
8. 用`S 规范`作为前缀表示**代码规范相关调整**，如`S 规范 消息接口代码格式修改`
9. 用`U 更新`作为前缀表示**根据业务需求调整做的修改**，如`U 更新 消息接口逻辑修改`

## 前后端协作建议

建议使用[石墨](https://shimo.im/)协作

## TODO List

- [ ] 用户配置角色接口
- [ ] 完善文档
- [ ] 完善数据库初始数据
