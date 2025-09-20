package JUP.mySynchronized;

public class MySyncDemo1 {


   static class SyncDemo {
        // 静态同步方法，锁是 SyncDemo.class
        public static synchronized void staticMethod() {
            // 临界区代码（如操作静态变量）
            System.out.println("线程："+Thread.currentThread() +" 获取锁");
            try {
                System.out.println(System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // 测试
        // 所有线程访问静态同步方法，都会竞争同一把锁（同步执行）
        new Thread(() -> SyncDemo.staticMethod()).start();
        new Thread(() -> SyncDemo.staticMethod()).start();
        new Thread(() -> SyncDemo.staticMethod()).start();

    }


}
