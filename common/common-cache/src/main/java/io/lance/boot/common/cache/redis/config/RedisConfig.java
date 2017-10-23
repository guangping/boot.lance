package io.lance.boot.common.cache.redis.config;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @desc: redis 配置 单节点
 * @author: lance
 * @time: 2017-10-23 16:58
 */
public class RedisConfig {

    /**
     * ip
     */
    private String host;

    /**
     * 密码
     */
    private String password;

    /**
     * 端口
     */
    private int port = 6379;

    /**
     * 默认库
     */
    private int database = 0;

    private int maxTotal = 10;

    private int minIdle = 1;

    private int maxIdle = 3;


    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPassword(password);
        factory.setPort(port);
        factory.setDatabase(database);

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出
        poolConfig.setMaxWaitMillis(3000);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        poolConfig.setTestOnBorrow(true);

        factory.setPoolConfig(poolConfig);

        return factory;
    }
}
