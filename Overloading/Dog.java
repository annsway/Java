
// Overloading
class Dog{
    public void bark(){
        System.out.println("woof ");
    }

    //overloading method
    public void bark(int num){
        for(int i=0; i<num; i++)
            System.out.println("doof ");
    }

    public static void main(String[] args){

        Dog dog = new Dog();

        dog.bark();
        dog.bark(5);

    }

}