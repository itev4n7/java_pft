package ru.stqa.pft.sandbox;

public class FirstProgram {

   public static void main(String[] args) {
      Square s = new Square(5);
      Rectangle r = new Rectangle(4, 6);
      System.out.println(s.area());
      System.out.println(r.area());
   }
}
