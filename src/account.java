//Per person
public class account {
    protected int accountNumber;
    protected String holderName;
    protected double balance;

    //constructor
    account(int _accountNumber, String _holderName){
        this.balance=0;
        this.accountNumber=_accountNumber;
        this.holderName=_holderName;
    }

    //database
    account(int _accountNumber, String _holderName, double _balance){
        this.balance=_balance;
        this.accountNumber=_accountNumber;
        this.holderName=_holderName;
    }


    public void deposit(double amount){
        balance+=amount;
        System.out.println("Your deposit was successful. Your account balance is $" + balance );  
        System.out.println();  
    }

    public boolean  withdraw(double amount){
        if(balance<amount){
            System.out.println("Insufficient funds. Your balance is: $" + balance);
            System.out.println();
            return false;
        }
        else{
            balance-=amount;
            System.out.println("Withdrawal successful! Your new balance is: $" + balance);
            System.out.println();
            return true;
        }
        
    }

    public void account_details(){
        System.out.println("Account type          : General Account ");
        System.out.println("Account number is     : " + accountNumber);
        System.out.println("Account holder's name : " + holderName);
        System.out.println("Account balance is    : " + balance);
    }

    //setter
    public void setName(String _holderName){
        holderName=_holderName;
        System.out.println("Your account name is updated successfully \n Your updated acount name is " + holderName);  
    }

    public void setBalance(double _balance){
        balance=_balance;
    }

    //getter
    public String getName(){
        System.out.println("The holders name is " + holderName);
        return holderName;
    }

    public double getBalance(){
        return balance;
    }

    public int getAccount(){
        return accountNumber;
    }


    
}
