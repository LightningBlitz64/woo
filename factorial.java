import java.util.Scanner;
class HelloWorld {
    public static void main(String[] args) {
        Scanner number = new Scanner(System.in);
        int userinput = number.nextInt();
        System.out.println(factorial(userinput));
        
    }
    public static int factorial(int userinput) {
        int fact = 1;
        for(int i = 1;  i <= userinput;  i++){
            fact = fact*i;
        }
        return fact;
    }
}
