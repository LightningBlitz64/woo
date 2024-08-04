import java.util.Scanner;

class Season {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String answer = "Valid";
        System.out.println(getSeason(1));
    }
    public static String getSeason(int month){
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Month Number.");
            month = scan.nextInt();
            String output = "";
            if (month == 12 || month < 3){
                return "This month is in Winter";
                
            }else if (month < 6){
                return "This month is in the Spring";
            
            }else if (month < 9){
                return "This month is in the Summer";
            
            }else if (month < 12){
                return "This month is in the Fall";
                
            }else{
                System.out.println("That is not a valid number.");
            }
        }
        
    }
}
