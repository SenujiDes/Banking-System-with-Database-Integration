import java.util.ArrayList;

public class goldAccountTester {
    public static void main(String[] args) {
        goldAccount acc= new goldAccount(12334,"Senuji", 34000);
        ArrayList<account> accounts= new ArrayList<>();
        accounts.add(acc);

        acc.account_details();
    }
    
}
