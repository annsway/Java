import java.io.*;
import java.net.URL;

/**
 *
 * Write a program that will display lines of a text you specify. It should take two arguments:
 * a file or a URL and the number of lines to display.
 * - If the number of lines given is positive, it should display the first n lines of the text.
 * - If it is 0, it should display all the lines of the text.
 * - If it is a negative number, it should display the last n lines of the text.
 * Your program should handle errors appropriately.
 *
 * **/

public class IOtask {

    public void readFile(String inputURL, int inputNo) throws IOException, InvalidLineNumException {

        URL url = new URL(inputURL);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        int lineEnd = inputNo, lineStart = 0;
        int lineCount = 0;

        // get the total number of lines of the page
        BufferedReader br_cnt = new BufferedReader(new InputStreamReader(url.openStream()));
        while(br_cnt.readLine()!=null){
            lineCount++;
        }
        br_cnt.close();

        // get the start and end line numbers to read
        if(inputNo < 0 && Math.abs(inputNo) <= lineCount){
            lineStart = lineCount - Math.abs(inputNo);
            lineEnd = lineCount;
            for(int j=0; j<lineStart; j++){
                br.readLine();
            }
        } else if (Math.abs(inputNo) > lineCount){
            System.out.println("Note: There is a total of " + lineCount + " line(s) in the file. ");
            throw new InvalidLineNumException("Error: Exceeds the maximum line number in the file!  ");
        } else if(inputNo == 0){
            lineEnd = lineCount;
        }

        // read and append the lines to the output string
        for(int i=lineStart; i<lineEnd; i++){
            line = br.readLine();
            int num = i+1;
            sb.append("line ").append(num).append(": ");
            sb.append(line);
            sb.append("\n");
        }

        br.close(); // close the stream and release the resources
        System.out.println("Contents of File: ");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException, InvalidLineNumException {
        IOtask task = new IOtask();
        task.readFile("https://sjmulder.nl/en/textonly.html", 10);
    }
}
