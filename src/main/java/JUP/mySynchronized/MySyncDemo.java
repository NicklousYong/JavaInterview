package JUP.mySynchronized;

/**
 * 这个类模拟了synchronized锁住非静态方法的行为。
 * 结果是：同一实例调用加锁方法，在同一时刻会存在竞争
 *        不同实例调用加锁方法，在同一时刻不存在锁竞争
 */
public class MySyncDemo {

   static class SyncDemo {
        // 非静态同步方法，锁是 this（当前实例）
        public synchronized void instanceMethod() {
            // 临界区代码
            System.out.println("线程："+Thread.currentThread() +" 获取锁，当前对象为： " +this.toString());
            try {
                Thread.sleep(10000); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





    public static void main(String[] args) {
        // 测试
        SyncDemo obj1 = new SyncDemo();
        SyncDemo obj2 = new SyncDemo();

        // 线程1和线程2访问同一实例，会竞争锁（同步执行）
//        new Thread(() -> obj1.instanceMethod()).start();
//        new Thread(() -> obj1.instanceMethod()).start();

        // 线程3和线程4访问不同实例，不竞争锁（并发执行）
        new Thread(() -> obj1.instanceMethod()).start();
        new Thread(() -> obj2.instanceMethod()).start();
    }



}
