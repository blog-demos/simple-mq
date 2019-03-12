package git.qwhai.mq.redis;

import git.qwhai.mq.conf.RedisConfig;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * @Author: Q-WHai
 * @Date: Created in 18:22 2019/03/12
 */
public class RedisHelper {

    private final Logger logger = Logger.getLogger(RedisHelper.class);

    private Jedis jedis = null;

    public RedisHelper() {
        connectRedis();
    }

    private void connectRedis() {
        if (null != jedis) return;

        logger.info("连接 Redis...");
        jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("Redis 连接成功");
    }

    public void registerChannel(byte[] channel) {
        jedis.publish(channel, null);
    }
}
