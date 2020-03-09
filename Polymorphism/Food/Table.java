public class Table {

    public static void main(String[] args) {

        Food[] foodArray = new Food[4];

        Food dimsum = new Dimsum();
        Dimsum dimsum1 = new Dimsum();

        Food pizza = new Pizza();
        Food apple = new Apple();

        foodArray[0] = dimsum;
        foodArray[1] = dimsum1;
        foodArray[2] = pizza;
        foodArray[3] = apple;

        for (int i=1; i<=foodArray.length-1; i++){
            foodArray[i].yum(); // Dynamic binding (late binding): the method of specific objects are called at runtime
        }

        // Early binding; overloading
        Pizza pizza1 = new Pizza();
        pizza1.yum(5);

        ((Apple) apple).type(); // cast Food type to Apple type to call the specific method in Apple()


    }

}
