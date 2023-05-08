package SystudyTest.redisLimit;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RTopic;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.BaseStatusListener;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * @Classname Test
 * @Description redis 限流器测试
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class TestSerialization {

    public static void main(String[] args) throws InterruptedException {

        createLimiter();
    }

    public static void createLimiter() {
        Config config = new Config();
        config.useSingleServer()
                .setTimeout(1000000)
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);
        User user = new User();
        user.setName("朱博");
        user.setSex("MALE");
        RTopic testTopic = client.getTopic("testTopic");
        testTopic.publish(user);
        testTopic.addListener(User.class, (charSequence, user1) -> System.out.println(user1.getName()));
    }


    static class User implements Serializable {

        private String name;

        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
