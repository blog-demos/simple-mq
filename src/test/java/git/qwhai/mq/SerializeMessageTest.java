package git.qwhai.mq;

import git.qwhai.mq.model.Message;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.*;

/**
 * @Author: Q-WHai
 * @Date: Created in 17:56 2019/03/12
 */
public class SerializeMessageTest {

    private final Logger logger = Logger.getLogger(SerializeMessageTest.class);

    @Test
    public void testSerialize() {
        Message message = new Message(10001, "Hello World");

        try {
            byte[] bytes = serialize(message);

            Message m1 = deserialize(bytes);
            logger.info(String.format("[%d: %s]", m1.getId(), m1.getContent()));
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private byte[] serialize(Message message) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(message);
        byte[] result = baos.toByteArray();
        logger.info("Message 对象序列化成功！");

        oos.close();
        baos.close();

        return result;
    }

    private Message deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);

        Message message = (Message) ois.readObject();
        logger.info("Message 对象反序列化成功！");

        ois.close();
        bais.close();

        return message;
    }
}
