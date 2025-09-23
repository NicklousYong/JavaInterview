package JUP.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用Callable创建线程的一个案例
 *
* */
// 实现Callable接口，泛型参数为返回值类型
class SumCalculator implements Callable<Integer> {
    private int num;

    // 构造方法，传入计算的上限值
    public SumCalculator(int num) {
        this.num = num;
    }

    // 重写call方法，实现计算逻辑
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
            // 模拟耗时操作
            Thread.sleep(10);
        }
        System.out.println(Thread.currentThread().getName() + " 计算完成，结果为: " + sum);
        return sum;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // 提交两个Callable任务
            // 这里的future为任务的结果，可以通过future.get()获取
            Future<Integer> future1 = executor.submit(new SumCalculator(100));
            Future<Integer> future2 = executor.submit(new SumCalculator(200));

            // 可以在这里做其他事情，不需要立即等待结果

            // 获取任务结果（如果未完成会阻塞等待）
            int result1 = future1.get();
            int result2 = future2.get();

            System.out.println("第一个任务结果: " + result1);
            System.out.println("第二个任务结果: " + result2);
            System.out.println("总和: " + (result1 + result2));
        } finally {
            // 关闭线程池
            executor.shutdown();
        }
    }
}
