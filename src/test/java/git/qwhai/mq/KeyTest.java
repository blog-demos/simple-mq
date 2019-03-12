package git.qwhai.mq;

import git.qwhai.mq.conf.RedisConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class KeyTest {

    private final Logger logger = Logger.getLogger(KeyTest.class);

    @Test
    public void testKeys() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        // 获取数据并输出
        logger.info("Keys:");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }

        jedis.close();
        logger.info("连接关闭");
    }
}
