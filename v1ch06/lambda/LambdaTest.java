package lambda;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * This program demonstrates the use of lambda expressions.
 * @version 1.0 2015-05-12
 * @author Cay Horstmann
 */
public class LambdaTest
{
   public static void main(String[] args)
   {
      /**
       * lambda表达式是一个可传递的代码块，可以在以后执行一次或多次
       * lambda表达式的形式：参数，箭头（->）以及一个表达式，如果代码要完成的计算无法放在一个表达式中，就可以像写方法一样，把这些代码放在{}中。
       * 以下注意五点：
       * 即使 lambda 表达式没有参数，仍然要提供空括号，就像无参方法一样；
       * 如果可以推导出一个 lambda 表达式的参数类型，则可以忽略其类型；
       * 如果方法只有一个参数，而且这个参数的类型可以推导得出，那么甚至还可以省略小括号；
       * 无需指定 lambda 表达式的返回类型，lambda 表达式的返回值类型总是会通过上下文推导得出；
       * 如果一个 lambda 表达式只在某些分支返回一个值，而另外一些分支不返回值，这是不合法的。
       *
       * 以下两个例子是显示了如何对一个比较器和一个动作监听器使用lambda表达式。
       * 以下两个例子都有一个共同点，都是将一个代码块传递到某个对象（一个定时器，或者一个Sort方法），这个代码块将会在未来的某个时间调用
       */
      var planets = new String[] { "Mercury", "Venus", "Earth", "Mars", 
         "Jupiter", "Saturn", "Uranus", "Neptune" };
      System.out.println(Arrays.toString(planets));
      System.out.println("Sorted in dictionary order:");
      Arrays.sort(planets);
      System.out.println(Arrays.toString(planets));
      System.out.println("Sorted by length:");
      Arrays.sort(planets, (first, second) -> first.length() - second.length());
      System.out.println(Arrays.toString(planets));
      /**
       * 对于只有一个抽象方法的接口，需要这种接口的对象时，就可以提供一个lambda表达式，这种接口就称为函数式接口（functional interface）
       * 注意常用的Predicate<T>和Supplier<T>这两个函数式接口；
       * System.out::println是一个方法引用，它指示编译器生成一个函数式接口的实例，覆盖这个接口的抽象方法来调用给定的方法。
       * 方法引用需要使用::运算符分隔方法名与对象名或类名，主要有三种情况
       * 1.object::instanceMethod 方法引用等价于向方法传递参数的lambda表达式
       * 2.Class::instanceMethod  方法引用等价于向第一个lambda的参数会成为隐式参数，其余的参数会传递到方法中
       * 3.Class::staticMethod    方法引用等价于所有参数都会传递到静态方法中
       *
       * eg：
       * 第一种情况：System.out::println           等价于   x->System.out.println(x)
       * 第二种情况：String::compareToIgnoreCase   等价于   (x,y)->x.compareToIgnoreCase(y)
       * 第三种情况：Math::pow                     等价于   (x,y)->Math.pow(x,y)
       *
       * 注意：只有当lambda表达式的方法体只调用一个方法而不做其他操作时，才能把lambda表达式重写为方法引用
       */
      ArrayList<String> list = new ArrayList<>();
      list.add("nnn");
      list.add("zzz");
      list.add("cccc");
      list.add(null);
      list.removeIf(e -> e.length() == 3);
      list.removeIf(Objects::isNull);
      list.stream().forEach(System.out::println);

      var timer = new Timer(1000, event ->
         System.out.println("The time is " + new Date()));
      timer.start();   
         
      // keep program running until user selects "OK"
      JOptionPane.showMessageDialog(null, "Quit program?");
      System.exit(0);         
   }
}
