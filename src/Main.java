import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        bank my_obj = new bank();
        goldAccount account= new goldAccount(1234,"Joen", 5000);


         System.out.println("Welcome to HNLC Bank");
        boolean quit =  false;
        while(!quit){
            System.out.println("\n*******************************************************************");
            System.out.println("*                      MENU OPTIONS                               *");
            System.out.println("*******************************************************************");
        
            System.out.println("1)Create a new  bank account"); 
            System.out.println("2)Delete your bank account"); 
            System.out.println("3)Deposit money"); 
            System.out.println("4)Withdraw money"); 
            System.out.println("5)Show accounts");
            System.out.println("6)Search an account");
            System.out.println("7)Quit");
            System.out.println("*******************************************************************\n");

            System.out.println();
            Scanner input=new Scanner(System.in);
            //System.out.print("Please select an option:");
            int option;
            while (true) {     
                System.out.print("Please select an option:");
                try{
                    option=input.nextInt();
                    input.nextLine(); //g cleared from the scanner buffet. Else it wont let you enter the name of the holder
                    if(option >0 && option <= 7){ //Checking whether the user has entered a valid option using error handling
                        break;
                    }
                    else{
                        System.out.println("Please enter a valid option ");
                        System.out.println();
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a valid option.");
                    input.nextLine();  //Invalid input is being cleared from the scanner buffet
                    System.out.println();

            
                }  
            }


            switch (option) {
                case 1:
                    my_obj.createAccount();
                    break;
                case 2:
                    my_obj.deleteAccount();
                    break;
                case 3:
                    my_obj.depositMoney();
                    break;
                case 4:
                    my_obj.withdrawMoney();  
                    break;
                case 5:
                    my_obj.showAccounts();;
                    break;
                case 6:
                    my_obj.showaccount();
                    break;
                case 7:
                    System.out.println("System Existing");
                    quit = true;
                    break;
                    
                default:
                    System.out.println("Invalid input.Please enter a correct number");
                    break;


            }
        }

    


    }
    
}
