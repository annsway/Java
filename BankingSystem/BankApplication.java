import java.util.ArrayList;

public class BankApplication {

    public static void addAccountToBank(Bank bank, int id, String name, String address, String phone, int cur_amt, int sav_amt) {

        addAccountToBank(bank, 3, "Abdullah Lang", "9927 Woodside Lane NY 11213 ", "999", 1156, -1);

        Customer customer = new Customer(id, name, address, phone);

        if(cur_amt>0) {
            Current_Account current_account = new Current_Account();
            current_account.setCustomer(customer);
            current_account.creditAmount(cur_amt);
            bank.addAccount(current_account);
        }

        if(sav_amt>0) {
            Saving_Account saving_account = new Saving_Account();
            saving_account.setCustomer(customer);
            saving_account.creditAmount(sav_amt);
            bank.addAccount(saving_account);
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank("TellsWells Bank");

        addAccountToBank(bank, 1, "Waylon Dalton", "351 Surrey Circle Brooklyn NY 11209", "999", 1356, 599);
        addAccountToBank(bank, 2, "Justine Henders", "9858 Glen Eagles Ave. NY 11237", "999", 156, 3599);
        addAccountToBank(bank, 3, "Abdullah Lang", "9927 Woodside Lane NY 11213 ", "999", 1156, -1);
        addAccountToBank(bank, 4, "Marcus Cruzing ", "71 Depot Lane Brooklyn NY 11212", "999", -1, 99);
        addAccountToBank(bank, 5, "Thalia Cobbing", "7233 NE. Summer St. NY 11235 ", "999", 2016, 35099);
        addAccountToBank(bank, 6, "Mathias Little", "87 Deerfield Ave. S. Valley NY 10977 ", "999", 3599, -1);
        addAccountToBank(bank, 7, "Eddie Randolph ", "718 East Howard Rd. NY 10977 ", "999", 7146, 9449);
        addAccountToBank(bank, 8, "Angela Walker ", "9768 Fieldstone Rd. Bronx, NY 10456 ", "999", 156, 3599);
        addAccountToBank(bank, 9, "Lia Shelton ", "226 High Noon Ave. Bronx, NY 10463 ", "999", 31356, 353399);
        addAccountToBank(bank, 10, "Hadassah Hartman", "642 Windfall Drive New York NY 11370", "999", 21256, 359922);
        addAccountToBank(bank, 11, "Joanna Shaffer", "99 Heritage St. New York, NY 10040 ", "999", 16, -1);
        addAccountToBank(bank, 12, "Jonathon Sheppard", "7 Cactus Ave. New York, NY 12550", "999", 56, 99);

        ArrayList<Account> allAccounts = bank.getAllAccounts();

        for (int i=0; i<allAccounts.size(); i++){
            allAccounts.get(i).PrintReports();

        }

        bank.removeAccount("Eddie Randolph ");
        bank.removeAccount("Eddie Randolph ");

        System.out.println("\n\nAfter removing Eddie\n\n");

        for (int i=0; i<allAccounts.size(); i++){
            allAccounts.get(i).PrintReports();

        }
    }

}



