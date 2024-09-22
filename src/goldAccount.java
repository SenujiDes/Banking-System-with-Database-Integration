public class goldAccount extends account {
    private double overDraftLimit;

    goldAccount(int _accountNumber, String _holderName, double _overDraftLimit){ //we need to provide the same values of the parent class(must) and then you can add new ones you'd wish to add
    super(_accountNumber,  _holderName);
    this.overDraftLimit= _overDraftLimit;
    }

    goldAccount(int _accountNumber, String _holderName,double balance, double _overDraftLimit){
        super(_accountNumber,  _holderName , balance);
        this.overDraftLimit= _overDraftLimit;
    }

    //Setter
    public void setOverDraftLimit(double _overDraftLimit){
        this.overDraftLimit= _overDraftLimit;
    }

    //Getter
    public double getOverDraftLimit(){
        return this.overDraftLimit;
    }

   @Override
   //here specifically we need to get same name, data type here.
   public boolean  withdraw(double amount1){ //we are hiding the complex interface but showing something simple here (abstraction)
    if(amount1 > balance +overDraftLimit){
            System.out.println("Insufficient funds. Your balance is: $" + balance);
            System.out.println();
            return false;
        }
        else{
            balance-=amount1;
            System.out.println("Withdrawal successful! Your new balance is: $" + balance);
            System.out.println();
            return true;
        } 
    
   }

   @Override
   public void account_details(){
    System.out.println("Account type          : Gold Account ");
    System.out.println("Account number is     : " + accountNumber);
    System.out.println("Account holder's name : " + holderName);
    System.out.println("Account balance is    : " + balance);
    System.out.println("your OD Limit is      : " + overDraftLimit);
}
}
