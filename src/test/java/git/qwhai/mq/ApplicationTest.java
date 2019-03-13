package git.qwhai.mq;

import git.qwhai.mq.model.Message;
import git.qwhai.mq.redis.RedisHelper;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: Q-WHai
 * @Date: Created in 11:19 2019/03/13
 */
public class ApplicationTest {

    @Test
    public void testPublish() {
        RedisHelper redis = new RedisHelper();

        Message message = new Message(1001, "Hello world.");

        try {
            redis.publishChannel(new byte[] { 0x01, 0x02, 0x03, 0x04 }, message.serialize());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSubscribe() {
        RedisHelper redis = new RedisHelper();
        redis.registerChannel(new byte[] { 0x01, 0x02, 0x03, 0x04 });
    }
}
