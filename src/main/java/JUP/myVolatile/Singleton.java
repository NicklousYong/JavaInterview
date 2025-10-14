package JUP.myVolatile;

/**
 * 单例模式创建对象的双重检查
 *  通过 “两次检查对象是否已初始化” 结合锁机制，既保证线程安全，又避免频繁加锁导致的性能损耗。其最经典的应用是单例模式的线程安全实现。
 */
public class Singleton {
    // 关键：用volatile修饰实例变量，禁止指令重排序
    private static volatile Singleton instance;

    // 私有构造方法，防止外部实例化
    private Singleton() {}

    // 双重检查的获取实例方法
    public static Singleton getInstance() {
        // 第一次检查：判断对象是否已经初始化，未初始化才进入后续逻辑（避免每次加锁）
        if (instance == null) {
            // 加锁：仅临界区代码需要同步
            synchronized (Singleton.class) {
                // 第二次检查：在同步代码块内再次判断对象是否已初始化，防止多线程并发进入同步代码块时重复创建对象（保证线程安全）。
                /* 为什么一定要用volatile? 因为instance = new Singleton()）在 JVM 中可分解为 3 步指令：
                *   1. 分配内存空间（memory = allocate()）
                    2. 初始化对象（ctorInstance(memory)）
                    3. 将instance指向内存空间（instance = memory）
                    * JVM 可能对步骤 2 和 3 进行重排序（优化性能），导致执行顺序变为1→3→2。此时：
                        线程 A 执行到3（instance已非null，但对象未初始化）；
                        线程 B 通过第一次检查（instance != null），直接返回未初始化的instance，后续使用时会抛出NullPointerException。
                      volatile的作用是禁止指令重排序，保证1→2→3的执行顺序，避免上述问题。
                * */
                if (instance == null) {
                    instance = new Singleton(); // 初始化对象
                }
            }
        }
        return instance;
    }
}


