package git.qwhai.mq;

import git.qwhai.mq.conf.RedisConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {

    private final Logger logger = Logger.getLogger(RedisTest.class);

    @Test
    public void testConnect() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        logger.info(String.format("连接状态：%s", jedis.ping()));

        jedis.close();
        logger.info("连接关闭");
    }
}
