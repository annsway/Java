import java.time.LocalDate;

class Current_Account extends Account{

    private double interest = 0.04;

    private LocalDate Date_of_Opening;

    public Current_Account()
    {
        this.Date_of_Opening = LocalDate.now();
    }

}
