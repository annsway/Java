public class Main {

    public static void main(String[] args){
        Balloon balloon = new Balloon();
        System.out.println("The current pressure of this balloon is "+balloon.getCurr()+"\n");
        balloon.inflate(50);
        System.out.println("The current pressure of this balloon is "+balloon.getCurr()+"\n");
        balloon.inflate(100.1);
        System.out.println("The current pressure of this balloon is "+balloon.getCurr()+"\n");
    }
}
