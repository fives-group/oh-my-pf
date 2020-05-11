# DIY Java Persistence Framework

### 1) 使用端

1. 配置文件
   1. 数据库配置(SQLMapConfig.yml)
      1. 数据库类型
      2. Auth 信息
      3. SQL 配置资源路径
   2. SQL 配置(Mapper.yml)
      1. 语句
      2. 参数类型
      3. 返回类型

### 2) 核心

本质上是对 JDBC 进行封装
1. 加载配置
   * 解析配置文件: 依赖 [eo-yaml]（https://github.com/decorators-squad/eo-yaml）
2. 将配置映射为 JavaBean
   1. SqlMapConfig.yml -> Configuration
   2. mapper.yml -> MappedStatement
3. 创建 SQLSessionFactory -> DefaultSQLSessionFactory 接口以及实现
   * openSession: 生产 sqlSession
4. 创建 SQLSession -> DefaultSQLSession
   * findAll
   * findOne
   * getById
   * update
   * delete
5. 创建 Executor -> SimpleExecutor
   * query(Configuration, MapperStatement, Object... params)

### 3) 总结
在实现过程中，由于使用了 `eo-yaml` 这个没有足够成熟的库，导致在配置文件中，SQL 不能换行问题未能解决。嘿嘿嘿嘿等更新 -。-  
项目上传到 Github： <https://github.com/fives-group/oh-my-pf>
* main: 框架源码
* test: 客户端测试源码
