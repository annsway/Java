
/**
 * Part 2. Define a class representing an inflatable balloon. Balloon should have a method inflate( )
 * (which can be called many times) and a maximum inflation pressure.
 * It should act appropriately if the Balloon is overinflated.
 * Since Balloon is a proper Java class, it shouldn't be possible to change the pressure without calling inflate( )!
 * **/
public class Balloon {

    private double curr, max=300;

    public Balloon(){
        this.curr = 150;
    }

    void validate(double newCurr) throws OverinflationException{
        if(newCurr>max)
            throw new OverinflationException("Invalid amount entered!");
        else
            System.out.println("Successfully inflated the balloon. ");
    }

    public void inflate(double amt) {
        try{
            double newCurr=curr+amt;
            validate(newCurr);
            curr=newCurr;
        } catch(Exception m){
            System.out.println("Exception occurred: "+m);
        }
    }

    public double getCurr(){
        return curr;
    }
}


