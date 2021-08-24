package innerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * This program demonstrates the use of inner classes.
 * @version 1.11 2017-12-14
 * @author Cay Horstmann
 */
public class InnerClassTest
{
   public static void main(String[] args)
   {
      /**
       * 内部类（inner class）是定义在另一个类中的类。使用内部类的原因主要有两点：
       * 1、内部类可以对同一个包中的其他类隐藏
       * 2、内部类可以访问定义这个类的作用域中的数据，包括原本私有的数据
       */
      var clock = new TalkingClock(1000, true);
      clock.start();

      // keep program running until the user selects "OK"
      JOptionPane.showMessageDialog(null, "Quit program?");
      System.exit(0);
   }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock
{
   private int interval;
   private boolean beep;

   /**
    * Constructs a talking clock
    * @param interval the interval between messages (in milliseconds)
    * @param beep true if the clock should beep
    */
   public TalkingClock(int interval, boolean beep)
   {
      this.interval = interval;
      this.beep = beep;
   }

   /**
    * Starts the clock.
    */
   public void start()
   {
      var listener = new TimePrinter();
      var timer = new Timer(interval, listener);
      timer.start();
   }

   public class TimePrinter implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         System.out.println("At the tone, the time is " 
            + Instant.ofEpochMilli(event.getWhen()));
         //内部类的对象总有一个隐式引用，指向创建它的外部类对象。
         //实际编译时为 if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
         if (beep) Toolkit.getDefaultToolkit().beep();
      }
   }

   public void start02()
   {
      /**
       * 局部内部类
       * 局部内部类不能有访问说明符（即 public 和 private ），局部类的作用域被限定在声明这个局部类的块中。
       * 局部类会对外部世界完全隐藏，除了start02方法外，没有任何方法能知道局部内部类的存在。
       *
       * 局部内部类与其他内部类相比，还有一个有点。它们不仅能够访问外部类的字段，还可以访问局部变量。不过那些
       * 局部变量必须是事实最终变量，这说明它们一旦赋值就绝不会改变。
       */
      class TimePrinter implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            System.out.println("At the tone, the time is "
                    + Instant.ofEpochMilli(event.getWhen()));
            //内部类的对象总有一个隐式引用，指向创建它的外部类对象。
            //实际编译时为 if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
            if (beep) Toolkit.getDefaultToolkit().beep();
         }
      }

      var listener = new TimePrinter();
      var timer = new Timer(interval, listener);
      timer.start();
   }
}
