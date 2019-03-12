package git.qwhai.mq;

import git.qwhai.mq.conf.RedisConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PublishTest {

    private final Logger logger = Logger.getLogger(PublishTest.class);

    @Test
    public void testPublish() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        jedis.publish("redisChat", "Redis is a great caching technique");
        jedis.publish("redisChat", "Learn redis by runoob.com");

        jedis.close();
        logger.info("连接关闭");
    }

    @Test
    public void testSubscribe() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(RedisConfig.HOST, RedisConfig.PORT);
        jedis.auth(RedisConfig.PASSWORD);
        logger.info("连接成功");

        JedisPubSub sub = new Subscriber();
        logger.info("准备订阅 redisChat 频道");
        jedis.subscribe(sub, "redisChat");

        jedis.close();
        logger.info("连接关闭");
    }

    class Subscriber extends JedisPubSub {

        @Override
        public void onPong(String pattern) {
            logger.info(String.format("onPong.pattern = %s", pattern));
        }

        @Override
        public void onMessage(String channel, String message) {
            logger.info(String.format("onMessage.channel = %s, onMessage.message = %s", channel, message));
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            logger.info(String.format("onSubscribe.channel = %s, onSubscribe.subscribedChannels = %d", channel, subscribedChannels));
        }

        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
            logger.info(String.format("onUnsubscribe.channel = %s, onUnsubscribe.message = %d", channel, subscribedChannels));
        }

        @Override
        public void onPMessage(String pattern, String channel, String message) {
            logger.info(String.format("onPMessage.pattern = %s, onPMessage.channel = %s, onPMessage.message = %s", pattern, channel, message));
        }

        @Override
        public void onPSubscribe(String pattern, int subscribedChannels) {
            logger.info(String.format("onPSubscribe.pattern = %s, onPSubscribe.message = %d", pattern, subscribedChannels));
        }

        @Override
        public void onPUnsubscribe(String pattern, int subscribedChannels) {
            logger.info(String.format("onPUnsubscribe.pattern = %s, onPUnsubscribe.message = %d", pattern, subscribedChannels));
        }
    }
}
