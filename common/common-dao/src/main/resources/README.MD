# 数据库相关配置

mybatis:
  config-location: mybatis-conf.xml
  mapperLocations: classpath*:mapper/**/*.xml
  type-aliases-package:
  check-config-location: true


spring:
    datasource:
        name: test
        url: jdbc:mysql://rds5lp6u7x9frn2b8446o.mysql.rds.aliyuncs.com:3306/dev_zcjb_qy?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
        username: qy_zcjb
        password: zcjb1@325
        # 使用druid数据源
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.jdbc.Driver
        filters: stat
        maxActive: 20
        initialSize: 1
        maxWait: 60000
        minIdle: 1
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: select 'x'
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxOpenPreparedStatements: 20