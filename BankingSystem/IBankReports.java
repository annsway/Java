import java.time.LocalDate;
import java.util.ArrayList;

public interface IBankReports {
    void PrintReports();

}


abstract class Account implements IBankReports {

    private int Acc_no;
    private double Balance=0.0;
    private Customer myCustomer;

    @Override
    public void PrintReports(){
        System.out.println(myCustomer.getCustName()+ " " +myCustomer.getAdress() +" "+ Balance);
    };

    ////////
    public int getAccountNUmber(){
        return this.Acc_no;

    };
    public double getBalance(){
        return this.Balance;

    };

    public void debitAmount(double amt){

        this.Balance = this.Balance - amt;
    };

    public void creditAmount(double amt){

        this.Balance = this.Balance + amt;
    };

    //////////

    public Customer getCustomer(){
        return this.myCustomer;

    };

    public void setCustomer(Customer obj){
        this.myCustomer = obj;

    }

}

///////////

class Saving_Account extends Account{

    private LocalDate Date_of_Opening;

    private double Min_Balance;

    public Saving_Account(){

        this.Date_of_Opening = LocalDate.now();

        this.Min_Balance = 100.00;

    }

    @Override
    public void debitAmount(double amt){
        if (amt > Min_Balance && super.getBalance() < Min_Balance*2){
            System.out.println("No enough funds. ");
        } else {
            super.debitAmount(amt);
        }

    }

    @Override
    public void creditAmount(double amt){
        if (amt < Min_Balance){
            System.out.println("Less than $100. ");
        }
        super.creditAmount(amt);

    }

}

class Current_Account extends Account{

    private double interest = 0.04;

    private LocalDate Date_of_Opening;

    public Current_Account()
    {
        this.Date_of_Opening = LocalDate.now();
    }

}

///////////

class Customer{

    private int Cust_ID;

    private String Name = "";

    private String Address = "";

    private String Phone = "";

    public Customer(int cust_id, String cust_name, String address, String phone){
        this.Cust_ID = cust_id;
        this.Name = cust_name;
        this.Address = address;
        this.Phone = phone;
    }

    public String getCustName(){
        return this.Name;
    }

    public String getAdress(){
        return this.Address;
    }

}

class Bank{
    private String name = "Awesome Bank";

    private String address = "My Bank Address, awesome city, awesome zip";

    ArrayList<Account> accounts = new ArrayList<Account>();

    public Bank(String name){
        this.name = name;
    }


    ///
    public void addAccount(Account account){
        accounts.add(account);

    };

    public Account getAccount(String name){

        for (int i=0; i<accounts.size(); i++){
            if (accounts.get(i).getCustomer().getCustName()==name){
                return accounts.get(i);
            }
        }
        return null;
    };

    public ArrayList getAllAccounts(){

        return this.accounts;
    };

    public void removeAccount(String name){
        for (int i=0; i<accounts.size(); i++){
            if (accounts.get(i).getCustomer().getCustName()==name){
                accounts.remove(i);
            }
        }

    };

}
