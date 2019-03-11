package git.qwhai.mq;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisTest {

    private final String HOST = "192.168.88.133";
    private final int PORT = 6379;

    @Test
    public void testConnect() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println("连接成功");

        System.out.println(String.format("连接状态：%s", jedis.ping()));
    }

    @Test
    public void testSet() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println("连接成功");

        jedis.set("key1", "www.baidu.com");
        System.out.println("redis 存储的字符串为: "+ jedis.get("key1"));
    }

    @Test
    public void testList1() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println("连接成功");

        // 存储数据到列表中
        jedis.lpush("list1", "Google");
        jedis.lpush("list1", "Taobao");
        jedis.lpush("list1", "Tencent");
        jedis.lpush("list1", "Baidu");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("list1", 0 ,2);
        for(int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }

    @Test
    public void testList2() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println("连接成功");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("list1", 0 ,-1);
        for(int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + jedis.lpop("list1"));
        }
    }

    /**
     * 此函数测试方法为：
     *  先让`list1`为空，运行此测试函数，再通过`redis-cli`或程序向`list1`中插入新数据
     */
    @Test
    public void testList3() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println("连接成功");
        System.out.println("列表项为: " + jedis.blpop(20, "list1"));
    }

    @Test
    public void testKeys() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println("连接成功");

        // 获取数据并输出
        System.out.println("Keys:");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }
}
