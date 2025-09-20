package JUP.Lock;

/**
 * n个线程同时获取N个资源而不会死锁的demo
 */
public class NoDeadlockDemo {
    // 1. 定义N个资源，并分配唯一编号（0,1,2）
    private static final Resource resource0 = new Resource(0);
    private static final Resource resource1 = new Resource(1);
    private static final Resource resource2 = new Resource(2);

    // 资源类：包含编号和同步方法（模拟资源操作）
    static class Resource {
        private final int id;
        public Resource(int id) { this.id = id; }
        public int getId() { return id; }

        // 模拟资源操作（需要同步）
        public synchronized void operate() {
            System.out.println(Thread.currentThread().getName() + " 操作资源" + id);
        }
    }

    // 2. 工具方法：按编号顺序获取资源（核心逻辑）
    private static void acquireResources(Resource r1, Resource r2) {
        Resource first = r1.getId() < r2.getId() ? r1 : r2; // 先获取小编号资源
        Resource second = r1.getId() < r2.getId() ? r2 : r1; // 再获取大编号资源

        synchronized (first) {
            System.out.println(Thread.currentThread().getName() + " 获取资源" + first.getId());
            try { Thread.sleep(100); } catch (InterruptedException e) {} // 模拟操作耗时

            synchronized (second) {
                System.out.println(Thread.currentThread().getName() + " 获取资源" + second.getId());
                first.operate(); // 操作资源
                second.operate();
            }
        }
    }

    public static void main(String[] args) {
        // 3. 启动N个线程，每个线程访问不同的资源组合，但获取顺序一致
        new Thread(() -> {
            acquireResources(resource0, resource1); // 线程1需要资源0和1
        }, "线程1").start();

        new Thread(() -> {
            acquireResources(resource1, resource2); // 线程2需要资源1和2
        }, "线程2").start();

        new Thread(() -> {
            acquireResources(resource0, resource2); // 线程3需要资源0和2
        }, "线程3").start();
    }
}
