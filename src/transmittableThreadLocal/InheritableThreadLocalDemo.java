package transmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalDemo {

    static class ParentThreadData {
        private final InheritableThreadLocal<String> data = new InheritableThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "default";
            }
        };

        public String getData() {
            return data.get();
        }

        public void setData(String data) {
            this.data.set(data);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ParentThreadData parentData = new ParentThreadData();

        // 父线程设置数据
        parentData.setData("parentValue");

        // 提交任务到线程池
        executorService.submit(() -> {
                // 子线程获取数据，此处应该获取父线程设置的值
                String childData = parentData.getData();
                System.out.println("Child thread data: " + childData);

                // 子线程修改数据
                parentData.setData("childValue");

                // 执行其他操作...
        });

        // 父线程继续执行其他操作...
        System.out.println("Parent thread continues to run...");

        // 关闭线程池
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}