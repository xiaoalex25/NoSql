package com.edu.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisUtil {

    /**
     * 初始化一个redis连接池
     * @return
     * @throws IOException
     */
    public static JedisPool initPool() throws IOException {
        InputStream inputStream = RedisUtil.class.getClass().getResourceAsStream("/redis-config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(Integer.valueOf(properties.getProperty("maxActive")));
        config.setMaxIdle(Integer.valueOf(properties.getProperty("maxIdle")));
        config.setMaxWaitMillis(Integer.valueOf(properties.getProperty("maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(properties.getProperty("testOnBorrow")));
        String[] address = properties.getProperty("ip").split(":");
        JedisPool pool = new JedisPool(config, address[0], Integer.valueOf(address[1]), Integer.valueOf(properties.getProperty("timeout")));
        return pool;
    }
}
