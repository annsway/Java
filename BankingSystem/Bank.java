import java.util.ArrayList;

class Bank{
    private String name = "Awesome Bank";

    private String address = "My Bank Address, awesome city, awesome zip";

    ArrayList<Account> accounts = new ArrayList<Account>();

    public Bank(String name){
        this.name = name;
    }

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