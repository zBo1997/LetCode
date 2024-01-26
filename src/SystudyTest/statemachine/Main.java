package SystudyTest.statemachine;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {
        // 初始化 Spring 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderServiceImpl orderService = (OrderServiceImpl)context.getBean("orderService");
        Order order = new Order();
        orderService.create(order);
        orderService.pay(order);
        orderService.deliver(order);
        orderService.receive(order);
        log.info("最终订单结果：{}", JSONObject.toJSONString(Persist.map));

    }
}
