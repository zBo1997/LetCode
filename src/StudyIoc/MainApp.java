package StudyIoc;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // 初始化 Spring 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());
        HelloBean helloBean1 = (HelloBean) context.getBean("helloBean");
        helloBean1.showMessage();
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                // 获取定义的 Bean
//                HelloBean helloBean1 = (HelloBean) context.getBean("helloBean");
//                HelloBean helloBean2 = (HelloBean) context.getBean("helloBean");
//
//                // 使用 Bean
//                helloBean1.showMessage();
//            }).start();
//        }
    }
}