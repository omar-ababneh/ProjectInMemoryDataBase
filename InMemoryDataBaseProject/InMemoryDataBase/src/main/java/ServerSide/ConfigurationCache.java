package ServerSide;

import redis.clients.jedis.JedisPoolConfig;
import java.time.Duration;

public class ConfigurationCache {

    public static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);//The maximum number of connections that are supported by the pool
        poolConfig.setTestOnBorrow(true);//Specifies whether to validate connections by using the PING command before the connections are borrowed from the pool. Invalid connections are removed from the pool.
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
