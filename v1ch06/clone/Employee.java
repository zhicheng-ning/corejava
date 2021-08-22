package clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 浅拷贝与深拷贝
 * 默认的克隆操作采用的是浅拷贝，即并不会克隆对象中所引用的其他子对象，如本Employee类中的String类和Date类。
 * 深拷贝则是会克隆对象中的所有其他子对象。
 * 就如本类中重写了clone方法，在其中对可变的Date子类对象也进行了克隆操作。
 * （注意：如果子对象属于一个不可变的类，如String，或者在对象的生命期中，子对象一直包含不变的常量，没有更改器方法会改变它，也没有方法会生成
 * 它的引用，那么这种情况下共享子对象是安全的，也就不需要重写clone方法，并指定public访问修饰符）
 */
public class Employee implements Cloneable
{
   private String name;
   private double salary;
   private Date hireDay;

   public Employee(String name, double salary)
   {
      this.name = name;
      this.salary = salary;
      hireDay = new Date();
   }

   public Employee clone() throws CloneNotSupportedException
   {
      // call Object.clone()
      Employee cloned = (Employee) super.clone();

      // 克隆可变的对象
      // clone mutable fields
      cloned.hireDay = (Date) hireDay.clone();

      return cloned;
   }

   /**
    * Set the hire day to a given date. 
    * @param year the year of the hire day
    * @param month the month of the hire day
    * @param day the day of the hire day
    */
   public void setHireDay(int year, int month, int day)
   {
      Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
      
      // example of instance field mutation
      hireDay.setTime(newHireDay.getTime());
   }

   public void raiseSalary(double byPercent)
   {
      double raise = salary * byPercent / 100;
      salary += raise;
   }

   public String toString()
   {
      return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
   }
}
