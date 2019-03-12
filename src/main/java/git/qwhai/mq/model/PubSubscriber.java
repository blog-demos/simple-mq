package git.qwhai.mq.model;

import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: Q-WHai
 * @Date: Created in 18:32 2019/03/12
 */
public class PubSubscriber extends JedisPubSub {

    private final Logger logger = Logger.getLogger(PubSubscriber.class);

    @Override
    public void onMessage(String channel, String message) {
        logger.info(String.format("onMessage.channel = %s, onMessage.message = %s", channel, message));
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        logger.info(String.format("onSubscribe.channel = %s, onSubscribe.subscribedChannels = %d", channel, subscribedChannels));
    }
}
