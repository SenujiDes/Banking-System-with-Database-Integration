import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankModel {
    public  boolean createAccount(account acc) throws SQLException {
        Connection con = DB_connection.connect(); //here "connect" is the one in the DB_collection , it's called here

        if (con != null) {
            String sql = "insert into bank_account( account_number,accountholder_name,balance) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, acc.getAccount());
            ps.setString(2, acc.getName());
            ps.setDouble(3, acc.getBalance());

            ps.executeUpdate();
            con.close();
            return true;

        } else {
            con.close();
            return false;
        }
    }

    public  boolean createGoldAccount(goldAccount acc) throws SQLException {
        Connection con = DB_connection.connect(); //here "connect" is the one in the DB_collection , it's called here

        if (con != null) {
            String sql = "insert into bank_account( account_number,accountholder_name,balance, od_limit , account_type) values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, acc.getAccount());
            ps.setString(2, acc.getName());
            ps.setDouble(3, acc.getBalance());
            ps.setDouble(4, acc.getOverDraftLimit());
            ps.setString(5,"Gold");

            ps.executeUpdate();
            con.close();
            return true;

        } else {
            con.close();
            return false;
        }
    }

    public  boolean deleteAccount(account acc) throws SQLException {
        Connection con = DB_connection.connect(); //here "connect" is the one in the DB_collection , it's called here

        if (con != null) {
            String sql = "DELETE FROM bank_account WHERE  account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, acc.getAccount());
            ps.executeUpdate();
            con.close();
            return true;

        } else {
            con.close();
            return false;
        }
    }

    //Used to generate account numbers.Else when we run it again the next person will get the same account number
    public int getMaxAccount() throws SQLException {
        Connection con = DB_connection.connect();

        if (con != null) {
            String sql = "SELECT MAX(account_number) from bank_account";
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, acc.getAccount());
            ResultSet rs = ps.executeQuery(); //if we ask sth from database, we have to keep it in the record set

            if (rs.next()) {
                int accNo = rs.getInt("max(account_number)");
                con.close();//object is created and inside the variables of the constructor we have to put database name
                return accNo; //Returning the account obj (type of "account")
            }
            con.close();
            return 0;
        }
        return 0;

    }

    public boolean updateBalance(double balance, int accountNum) throws SQLException{
        Connection con=DB_connection.connect();
        if(con!= null){
            String sql= "UPDATE bank_account SET balance=? WHERE account_number=?";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setDouble(1,balance);
            ps.setInt(2,accountNum);
            ps.executeUpdate();
            con.close();
            return true;

        }
        else{
            return  false;
        }

    }

    public void show_accounts(int accNum,int count) throws SQLException{
        Connection con= DB_connection.connect();
        if(con != null){
            ResultSet rs;
            if (count==0) {
                String sql = "SELECT * from bank_account";
                PreparedStatement ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            else{
                String sql ="SELECT * from bank_account where account_number=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,accNum);
                rs = ps.executeQuery();
            }

            while (rs.next()) {
                // Assuming your table has columns: account_number, account_holder, balance
                int accountNumber = rs.getInt("account_number"); // Fetch the account number
                String accountHolder = rs.getString("accountholder_name"); // Fetch the account holder's name
                double balance = rs.getDouble("balance"); // Fetch the account balance
                String account_type = rs.getString("account_type");
                double Od_limit = rs.getDouble("od_limit");

                // Display the account information
                System.out.println("Account Number: " + accountNumber);
                System.out.println("Account Holder: " + accountHolder);
                System.out.println("Balance       : " + balance);
                System.out.println("Account Type  : "+  account_type);
                if(account_type.equals("Gold")) {
                    System.out.println("OD limit      : " + Od_limit);
                }
                System.out.println("------------------------");
            }

            con.close(); // Close the connection

        }
        else{
            System.out.println("Connection issue");
        }
    }



        //this is in a another scope
        public account searchAccount(int accountNumber) throws SQLException{
            Connection con =DB_connection.connect();
            if(con != null){
                String sql="select * from bank_account where account_number=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,accountNumber);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    account acc = new account(rs.getInt("account_number"), rs.getString("accountholder_name") , rs.getDouble("balance"));
                    con.close();//object is created and inside the variables of the constructor we have to put database name
                    return acc; //Returning the account obj (type of "account")
                }
                con.close();
                return null;
            }
            return null;
        }

        //For the gold accounts and can be used to general accounts as well since the OD limit is 0 in general accounts
    public goldAccount searchGoldAccount(int accountNumber) throws SQLException{
        Connection con =DB_connection.connect();
        if(con != null){
            String sql="select * from bank_account where account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,accountNumber);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                goldAccount acc = new goldAccount(rs.getInt("account_number"), rs.getString("accountholder_name") , rs.getDouble("balance") , rs.getDouble("od_limit") );
                con.close();//object is created and inside the variables of the constructor we have to put database name
                return acc; //Returning the account obj (type of "account")
            }
            con.close();
            return null;
        }
        return null;
    }

    }

