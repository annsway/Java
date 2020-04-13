import java.time.LocalDate;

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