package basic.Finally;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试finally代码块
 */
public class testFinally {
    public static void main(String[] args) {
        int result = test();
        System.out.println("方法返回值：" + result);

    }

    public static int test() {
        int num = 1;
        try {
            return num; // 记录返回值 1
        } finally {
            num = 2; // 修改变量，不影响返回值
            System.out.println("finally 中 num = " + num);
        }
    }
// 输出：finally 中 num = 2 → 方法返回值：1


    public static List<Integer> test1() {
        List<Integer> list = new ArrayList<>();
        try {
            list.add(1);
            return list; // 返回 list 引用
        } finally {
            list.add(2); // 修改对象内容
        }
    }
    // 方法返回的 list 包含 [1, 2]



}