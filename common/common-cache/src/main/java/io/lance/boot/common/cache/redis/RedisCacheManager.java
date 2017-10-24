package io.lance.boot.common.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.lance.boot.common.cache.ICache;
import io.lance.boot.common.core.exception.EbsException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @desc: redis 操作
 * @author: lance
 * @time: 2017-10-24 10:28
 */
@Component
public class RedisCacheManager implements ICache {

    private static final Logger logger = LogManager.getLogger();


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, int expire) {
        redisTemplate.opsForValue().set(key, String.valueOf(value), expire, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        Object object = redisTemplate.opsForValue().get(key);
        return (null == object) ? "" : object.toString();
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public long incr(String key, long by) {
        return redisTemplate.opsForValue().increment(key, by);
    }

    @Deprecated
    @Override
    public long incr(String key, long by, long def) {
        return 0;
    }

    public <T> T getObj(String key, TypeReference<T> type) {
        String str = this.get(key);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return this.json2Object(str, type);
    }

    @Override
    public <T> T getObj(String key, Class<T> clazz) {
        if (clazz == null)
            return null;
        String str = this.get(key);
        if (clazz.getName().equals("java.util.List")) {
            return json2Object(str, new TypeReference<T>() {
            });
        }
        if (clazz.getName().equals("java.lang.String")) {
            return (T) str;
        }
        return json2Object(str, clazz);
    }

    private <T> T json2Object(String jsonString, TypeReference<T> tr) {
        try {
            return JSON.parseObject(jsonString, tr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private <T> T json2Object(String jsonString, Class<T> clazz) {
        try {
            return JSON.toJavaObject(JSON.parseObject((jsonString)), clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public void set(String key, Object value) {
        if (null == value) {
            throw new EbsException("value is null");
        }
        String str = JSONObject.toJSONString(value);
        this.set(key, str);
    }

    @Override
    public void set(String key, Object value, int expire) {
        if (null == value) {
            throw new EbsException("value is null");
        }
        String str = JSONObject.toJSONString(value);
        this.set(key, str, expire);
    }

    /**
     * @desc: 存在返回false, 否则返回true TODO 可实现分布式锁
     * @author: lance
     * @time: 2017-10-24 10:52:01
     */
    public boolean setNx(String key, String value) {
        final RedisSerializer<String> serializer = this.redisTemplate.getStringSerializer();
        Boolean sign = (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                boolean val = redisConnection.setNX(serializer.serialize(key), serializer.serialize(value));
                return val;
            }
        });
        return sign;
    }
}
