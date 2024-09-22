import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class bank {
    Scanner input = new Scanner(System.in);

    //Validate - whether the user input a int or not
    public int validate_int(String question){
       while (true){
           System.out.print(question);
           try{
               int ans = input.nextInt();
               input.nextLine();
               return ans;
           }
           catch (InputMismatchException e){
               System.out.println("Invalid integer. Please enter a valid integer! \n");
               input.nextLine();
           }
       }
    }

    //Validate - whether the user input a double  or not
    public double validate_double(String question){
        while (true){
            System.out.print(question);
            try{
                double ans = input.nextDouble();
                input.nextLine();
                return ans;
            }
            catch (InputMismatchException e){
                System.out.println("Invalid amount. Please enter a valid amount! \n");
                input.nextLine();
            }
        }
    }

    public void createAccount() throws SQLException {
        System.out.println("\n*******************************************************************");
        System.out.println("*                   SUB MENU OPTIONS                              *");
        System.out.println("*******************************************************************");
        System.out.println("1)Gold account");
        System.out.println("2)General account");
        System.out.println();
        System.out.print("Choose the account category: ");
        int accCatergry = input.nextInt();
        input.nextLine();

        if(accCatergry== 1){
            addGoldAccount();
        }
        else if(accCatergry==2){
           addAccount();
        }
        else{
            System.out.println("Invalid Option");
        }
    }


    public boolean addGoldAccount( ) throws SQLException {

        System.out.print("Enter the account holder's name : ");
        String nameIn = input.nextLine();



        BankModel bankmodel = new BankModel();
        int AccountNumberIn=bankmodel.getMaxAccount();

        if (AccountNumberIn==0){
            AccountNumberIn=1000;
        }
        else{
            AccountNumberIn++;
        }
        account acc=bankmodel.searchAccount(AccountNumberIn);

        if(acc==null){
            double OD_limit = validate_double("Enter the OD Limit: ");
            goldAccount newAccount= new goldAccount(AccountNumberIn, nameIn, OD_limit);
            bankmodel.createGoldAccount(newAccount);
            System.out.println("Your account number is : " + AccountNumberIn);
            return true;
        }
        return false;
    }

    //Using DBs
    public boolean addAccount( ) throws SQLException {

        System.out.print("Enter the account holder's name : ");
        String nameIn = input.nextLine();

        BankModel bankmodel = new BankModel();
        int AccountNumberIn=bankmodel.getMaxAccount();

        if (AccountNumberIn==0){
            AccountNumberIn=1000;
        }
        else{
            AccountNumberIn++;
        }
        account acc=bankmodel.searchAccount(AccountNumberIn);

        if(acc==null){
            account newAccount= new account(AccountNumberIn, nameIn);
            bankmodel.createAccount(newAccount);
            System.out.println("Your account number is : " + AccountNumberIn);
            return true;
            }
            return false;
        }


    public void deleteAccount() throws SQLException {

        //Validation
        int accNum = validate_int("If you wish to delete your account please enter your account number: ");

        BankModel bankmodel = new BankModel();
        account acc=bankmodel.searchAccount(accNum); //As in the "BankModel" we return "account" type data, we need to catch itb as a "account" type data

        if(acc==null){
            System.out.println("Sorry, The account number you have provided does not exist");
            System.out.println();
        }
        else{
            bankmodel.deleteAccount(acc);
            System.out.println("Your account has been removed successfuly");
            System.out.println();

        }
    }

    public void depositMoney() throws SQLException {
        //Validation
        int accNum = validate_int("Please enter you account number: ");

        BankModel bankmodel = new BankModel();
        account account = bankmodel.searchAccount(accNum);

        if (account!=null) {
            //System.out.print("Please enter the amount of cash you want to deposit: $");
            //double deposit_amount = input.nextDouble();

            double deposit_amount = validate_double("Please enter the amount of cash you want to deposit: $");


            //adding the previous acc balance to the new deposited amount
            double newBalance=deposit_amount+account.getBalance();
            bankmodel.updateBalance(newBalance,account.getAccount());
            System.out.println("Successfuly deposited");
            System.out.println("Your account balance is: $" + newBalance);

        } else {
            System.out.println("Sorry, The account number you have provided does not exist");
            System.out.println();
        }
    }
    public void withdrawMoney() throws SQLException {
        int accNum = validate_int("Please enter your account number: ");

        BankModel bankmodel = new BankModel();
        goldAccount acc=bankmodel.searchGoldAccount(accNum);

        if (acc != null) {
            System.out.println("Your account balance is: " + acc.getBalance());
            if (acc.getOverDraftLimit()!=0){
                System.out.println("Your OD Limit is: " + acc.getOverDraftLimit());
            }
            //Validation
            double withdraw_amount = validate_double("Please enter the amount of cash you want to withdraw: $");
            if (acc.getBalance() + acc.getOverDraftLimit() > withdraw_amount) {
                double newBalance = acc.getBalance() - withdraw_amount;
                bankmodel.updateBalance(newBalance, accNum);
                System.out.println("Successfuly withdrawd");
                System.out.println("Your account balance is: " + newBalance);
            }
            else{
                System.out.println("Sorry, Insuffiecent balance");
            }
        }


        else {
            System.out.println("Sorry, The account number you have provided does not exist");
            System.out.println();
        }

    }

    public void showAccounts() throws SQLException{

        System.out.println("Display Accounts");
        System.out.println();

        BankModel bank = new BankModel();
        bank.show_accounts(0,0);
    }

    public void showaccount() throws SQLException{
        int accNum = validate_int("Please enter your account number: ");

        BankModel bankmodel = new BankModel();
        goldAccount acc=bankmodel.searchGoldAccount(accNum);

        if(acc != null){
            bankmodel.show_accounts(accNum,1);
        }
        else {
            System.out.println("Sorry, The account number you have provided does not exist");
            System.out.println();
        }



    }

}
