/*
Welcome to JDoodle!

You can execute code here in 88 languages. Right now you’re in the Java IDE.

  1. Click the orange Execute button ▶ to execute the sample code below and see how it works.

  2. Want help writing or debugging code? Type a query into JDroid on the right hand side ---------------->

  3.Try the menu buttons on the left. Save your file, share code with friends and open saved projects.

Want to change languages? Try the search bar up the top.
*/
import java.util.Scanner;
public class MyClass {
  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);
    
    double num1 = input.nextDouble();
    
    String op = input.next();
    
    double num2 = input.nextDouble();
    
    System.out.println(Calc(num1, op, num2));
    
    
    
      
   
      
  }
  public static double Calc(double num1, String op, double num2){
      if (op.equals("+")){
        double answer = num1 + num2;
        return answer;
    }else if (op.equals("-")){
        double answer = num1 - num2;
        return answer;
    }else if (op.equals("*")){
        double answer = num1*num2;
        return answer;
    }else{
        double answer = num1/num2;
        return answer;
    }
    
      
  }
    
}
