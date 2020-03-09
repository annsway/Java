public class Pizza extends Food {


    public void yum(){
        System.out.println("Pizza is yum!");
    }

    // Overloading; Static (early) binding
    public void yum(int scale){
        System.out.println("Pizza is yum at scale "+scale+"!");
    }
}
