package clone;

import java.util.Arrays;

/**
 * This program demonstrates cloning.
 *
 * @author Cay Horstmann
 * @version 1.11 2018-03-16
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * 测试深拷贝，深拷贝得到的对象是copy，与原始对象original是相互独立，互不影响的。
         * 就像copy对象更改了Employee对象中的Date子对象，但是原始的original对象中的Date对象并未受到影响。
         *
         * 所有的数组类型都有一个公共的clone方法，而不是受保护的。可以使用这个方法建立一个新的数组，包含原数组所有元素的副本
         */
        var original = new Employee("John Q. Public", 50000);
        original.setHireDay(2000, 1, 1);
        Employee copy = original.clone();
        copy.raiseSalary(10);
        copy.setHireDay(2002, 12, 31);
        System.out.println("original=" + original);
        System.out.println("copy=" + copy);

        System.out.println("===========");
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 6};
        System.out.println("nums=" + nums);
        System.out.println(Arrays.toString(nums));
        final int[] copyNums = nums.clone();
        System.out.println("copyNums=" + copyNums);
        System.out.println(Arrays.toString(copyNums));

    }
}
