package JUP.Lock;

public class DeadlockDemo {
    // 定义两把锁
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        // 线程1：先获取lockA，再尝试获取lockB
        new Thread(() -> {
            synchronized (lockA) {
                System.out.println("线程1已获取lockA，等待lockB...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lockB) {
                    System.out.println("线程1已获取lockB，执行完成");
                }
            }
        }).start();

        // 线程2：先获取lockB，再尝试获取lockA
        new Thread(() -> {
            synchronized (lockB) {
                System.out.println("线程2已获取lockB，等待lockA...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lockA) {
                    System.out.println("线程2已获取lockA，执行完成");
                }
            }
        }).start();
    }
}