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

### HTTP 请求设计

1. `GET` 请求用于获取资源
2. `PUT` 请求用于完全修改资源
3. `PATCH` 请求用于部分修改资源
4. `POST` 请求用于新增资源
5. `DELETE` 请求用于删除资源
6. 请求 URL 中不应包含 `get`, `create`, `delete`, `update` 等操作词
7. 请求 URL 中出现多个单词应用 `kebab-case(横杠式命名风格)`，如：`recharge-record`

### Controller

1. 在`com.github.ifrankwang.spring.api.controller`包中创建`Controller`类
2. 所有接口类的`Endpoint`需以`API_PREFIX`起头
3. 不需要权限访问的接口，可以加上`NO_AUTH_URL`
4. 所有需要显示在Swagger中的接口类需要标注`@Api(tags = [描述])`
5. 所有接口方法需要标注`@ApiOperation(value = "[操作]", notes = "[描述]")`
6. 需要控制访问用户身份的接口，TODO 之后加上说明
7. 所有除操作成功状态外，有返回数据的，都写明返回类型。如果返回类型不是基本类型（`String`, `Integer`...），需要在类里面给每个属性标注`@ApiModelProperty("[说明]")`
8. 业务逻辑放在`facade`里处理；`Service`作为相关信息服务，只提供最基础的业务处理；`Controller`只做调用

### Service

TODO 之后加上说明

### Repository

TODO 之后加上说明

### Entity

1. `Entity`在数据库存储的状态、类型等，在代码中需要用相应的`enum`来体现
2. `Entity`的`List`, `Set`类型属性都必须初始化

### Git Commit

Git `commit`的时候，按照以下消息规范的范围进行`commit`。最好不要出现一次`commit`同时出现多个范围的修改，如：一次`commit`中新增了接口，同时又修改了配置。

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