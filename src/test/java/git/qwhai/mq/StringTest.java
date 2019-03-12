package git.qwhai.mq;

import git.qwhai.mq.conf.RedisConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class StringTest {

    private final Logger logger = Logger.getLogger(RedisTest.class);

    @Test
    public void testString() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        jedis.set("key1", "www.google.com");
        logger.info(String.format("redis 存储的字符串为: %s", jedis.get("key1")));

        jedis.close();
        logger.info("连接关闭");
    }
}
