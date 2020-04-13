
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
