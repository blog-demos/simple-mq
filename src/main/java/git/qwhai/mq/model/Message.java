package git.qwhai.mq.model;

import java.io.*;

public class Message implements Serializable {

    private static final long serialVersionUID = 3507279490382555478L;

    private int id;
    private String content;

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(this);
        byte[] result = baos.toByteArray();

        oos.close();
        baos.close();

        return result;
    }

    public static Message deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);

        Message message = (Message) ois.readObject();

        ois.close();
        bais.close();

        return message;
    }
}
