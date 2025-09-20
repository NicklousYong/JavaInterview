package JUP.DaemonThread;

/**
 * 守护线程示例
 */
public class DaemonDemo {
    public static void main(String[] args) {
        // 1. 用户线程：默认创建，执行核心逻辑
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("用户线程执行：" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("用户线程执行完毕");
        }, "UserThread");

        // 2. 守护线程：需手动设置，执行辅助逻辑
        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (true) { // 无限循环，模拟持续服务
                System.out.println("守护线程运行中... 计数：" + count++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DaemonThread");
        daemonThread.setDaemon(true); // 设置为守护线程（必须在 start() 前）

        // 启动线程
        userThread.start();
        daemonThread.start();

        // 主线程（用户线程）等待 2 秒后结束
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程（用户线程）执行完毕");
    }
}