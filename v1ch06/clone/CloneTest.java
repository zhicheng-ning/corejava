package clone;

/**
 * This program demonstrates cloning.
 * @version 1.11 2018-03-16
 * @author Cay Horstmann
 */
public class CloneTest
{
   public static void main(String[] args) throws CloneNotSupportedException
   {
      /**
       * 测试深拷贝，深拷贝得到的对象是copy，与原始对象original是相互独立，互不影响的。
       * 就像copy对象更改了Employee对象中的Date子对象，但是原始的original对象中的Date对象并未受到影响。
       */
      var original = new Employee("John Q. Public", 50000);
      original.setHireDay(2000, 1, 1);
      Employee copy = original.clone();
      copy.raiseSalary(10);
      copy.setHireDay(2002, 12, 31);
      System.out.println("original=" + original);
      System.out.println("copy=" + copy);
   }
}
