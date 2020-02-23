import java.util.ArrayList;
import java.lang.Character;
import java.util.Scanner;

public class BinaryTranslator {

    public static void main(String[] args) {

        System.out.println("Enter 'text' to convert text to binary number; enter 'binary' to convert binary number to decimal number: ");

        Scanner input = new Scanner(System.in);

        String userChoice = input.next();

        if (userChoice.equals("binary")) {
            System.out.println("Enter a binary number: ");
        } else if (userChoice.equals("text")){
            System.out.println("Enter a text: ");
        }

        String userInput = input.next();
        converter(userChoice, userInput);
    }

    private static void converter (String userChoice, String userInput){

        if (userChoice.equals("binary")){

            BinaryToNum(userInput);

        } else if(userChoice.equals("text")) {

            for (int i=0; i< userInput.length(); i++){

                ArrayList<Character> test = charToBinary(userInput.charAt(i));

                for (int j=0; j<test.size(); j++){
                    System.out.print(test.get(j));
                }

                System.out.print(" ");

            }
        }

    }


    private static ArrayList<Character> charToBinary(char inputChar){

        ArrayList<Character> binary = new ArrayList<>();
        ArrayList<Character> binaryRev = new ArrayList<>();

        int toDecimal = 0;

        if (Character.isDigit(inputChar)){

            toDecimal = (inputChar-'0');

        } else if (Character.isLetter(inputChar)){

            if (Character.isLowerCase(inputChar)){
                toDecimal = 97 + (inputChar - 'a');
            }
            else if (Character.isUpperCase(inputChar)){
                toDecimal = 65 + (inputChar - 'A');
            }
        }

        if (inputChar == '.'){
            binary.add('0');
            binary.add('0');
            binary.add('1');
            binary.add('0');
            binary.add('1');
            binary.add('1');
            binary.add('1');
            binary.add('0');
            return binary;
            
        } else {

            while(toDecimal !=0 ){
                int r = toDecimal % 2;
                toDecimal = toDecimal / 2;
                binary.add((char)(r+'0'));
            }

            for (int i=0; i<8; i++){
                if (binary.size() != 8) {
                    binary.add('0');
                }
            }

            for(int i=7; i>=0; i--){
                binaryRev.add(binary.get(i));
            }

            return binaryRev;

        }

    }

    private static void BinaryToNum(String inputBinary){

        double decimal = 0.0;

        int indexOfDot = -1;

        for(int i=0; i<inputBinary.length(); i++){

            if (inputBinary.charAt(i)=='.'){ // when current char is dot
                indexOfDot = i;
            } else if(indexOfDot < 0){ // before dot
                decimal = decimal*2 + (inputBinary.charAt(i)=='1'? 1: 0);
            } else { // after dot
                decimal = decimal + (inputBinary.charAt(i)=='1'? 1: 0)/Math.pow(2,i-indexOfDot);
            }
        }

        String outputStr = Double.toString(decimal);
        System.out.print(outputStr);

    }
}
