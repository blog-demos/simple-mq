package git.qwhai.mq.model;

import org.apache.log4j.Logger;
import redis.clients.jedis.BinaryJedisPubSub;

import java.io.IOException;

/**
 * @Author: Q-WHai
 * @Date: Created in 18:32 2019/03/12
 */
public class RedisChannelListener extends BinaryJedisPubSub {

    private final Logger logger = Logger.getLogger(RedisChannelListener.class);

    @Override
    public void onMessage(byte[] channel, byte[] message) {
        try {
            Message m1 = Message.deserialize(message);
            logger.info(String.format("接收到来自 %d 的消息：%s", m1.getId(), m1.getContent()));
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onSubscribe(byte[] channel, int subscribedChannels) {
        logger.info("RedisChannelListener onSubscribe.");
    }
}
