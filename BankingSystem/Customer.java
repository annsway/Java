
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