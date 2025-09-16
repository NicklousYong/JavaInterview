package basic.hash;

/**
 * 测试java的hash算法
 */
public class HashTest {

    public static void main(String[] args) {

        String  abc = "abc";
        int i = abc.hashCode();
        // 上一步方法中调用，计算出hash值，赋值给String的hash属性
        System.out.println("i = " + i);
        int j = abc.hashCode();

        System.out.println("j = " + j);




    }

    /**
     * String中的HashCode方法
     * @param value
     * @return
     */
    public static int hashCode(byte[] value) {
        int h = 0;
        for (byte v : value) {
            /**
             * v & 0xff 将byte转为int，防止负数参与运算
             * 1. 31是一个质数，能有效减少hash冲突
             * 2. 31可以被优化为移位和减法，31 * i == (i << 5) - i
             * 3.0xff是31的二进制表示，位运算效率更高
             */
            h = 31 * h + (v & 0xff);
        }
        return h;
    }



}
