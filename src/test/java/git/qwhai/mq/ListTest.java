package git.qwhai.mq;

import git.qwhai.mq.conf.RedisConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ListTest {

    private final Logger logger = Logger.getLogger(ListTest.class);

    @Test
    public void testList1() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        // 存储数据到列表中
        jedis.lpush("list1", "Google");
        jedis.lpush("list1", "Taobao");
        jedis.lpush("list1", "Tencent");
        jedis.lpush("list1", "Baidu");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("list1", 0 ,2);
        for(int i = 0; i < list.size(); i++) {
            logger.info(String.format("列表项为: %s", list.get(i)));
        }

        jedis.close();
        logger.info("连接关闭");
    }

    @Test
    public void testList2() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("list1", 0 ,-1);
        for(int i = 0; i < list.size(); i++) {
            logger.info(String.format("列表项为: %s", jedis.lpop("list1")));
        }

        jedis.close();
        logger.info("连接成功");
    }

    /**
     * 此函数测试方法为：
     *  先让`list1`为空，运行此测试函数，再通过`redis-cli`或程序向`list1`中插入新数据
     */
    @Test
    public void testList3() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");
        logger.info(String.format("列表项为: %s", jedis.blpop(20, "list1")));

        jedis.close();
        logger.info("连接关闭");
    }

}
