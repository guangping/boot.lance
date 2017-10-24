package io.lance.boot.common.cache.redis.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @desc: redis 配置 单节点
 * @author: lance
 * @time: 2017-10-23 16:58
 */
@Configuration
public class RedisConfig {

    private static final Logger logger = LogManager.getLogger();

    private static int maxTotal = 10;

    private static int timeOut = 3000;

    @Autowired
    private RedisProperties redisProperties;


    @Primary
    @Bean
    public JedisConnectionFactory connectionFactory() {
        logger.info("获取redis connection ....");
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(this.redisProperties.getHost());
        factory.setPassword(this.redisProperties.getPassword());
        factory.setPort(this.redisProperties.getPort());
        factory.setDatabase(this.redisProperties.getDatabase());
        factory.setTimeout(timeOut);
        if (this.redisProperties.getTimeout() > 0) {
            factory.setTimeout(this.redisProperties.getTimeout());
        }
        RedisProperties.Pool pool = this.redisProperties.getPool();

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        if (null != pool) {
            poolConfig.setMaxIdle(pool.getMaxIdle());
            poolConfig.setMinIdle(pool.getMinIdle());
        }
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出
        poolConfig.setMaxWaitMillis(3000);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        poolConfig.setTestOnBorrow(true);

        factory.setPoolConfig(poolConfig);

        return factory;
    }


    @Primary
    @Bean
    public RedisTemplate<String, ?> getRedisTemplate() {
        RedisTemplate<String, ?> redisTemplate = new StringRedisTemplate(connectionFactory());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisTemplate;
    }


}
