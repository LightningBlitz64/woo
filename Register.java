/*
Welcome to JDoodle!

You can execute code here in 88 languages. Right now you’re in the Java IDE.

  1. Click the orange Execute button ▶ to execute the sample code below and see how it works.

  2. Want help writing or debugging code? Type a query into JDroid on the right hand side ---------------->

  3.Try the menu buttons on the left. Save your file, share code with friends and open saved projects.

Want to change languages? Try the search bar up the top.
*/

public class MyClass {
  public static void main(String args[]) {
      Register name = new Register(10);
      System.out.println(name.price);
  }
}

class Register {
    double price;
    List<Integer> values = Array.asList(10000, 5000, 2000, 1000, 500, 100 );
    
    Register(double price){
        this.price = price;
    }
    
    void changePrice(double price) {
        this.price = price;
    }
    
    double getChange(double money){
        return money - price;
    }
    
    List<Integer> getCash(double money){
        int change = (int)(100*getChange(money));
        
        int hundreds = change/100;
        change %= 100;
        return new ArrayList<>();
    }
}
