import java.io.*;
import java.nio.charset.Charset;

public class ConvertCharset {

    public static void main(String[] args) throws IOException {
        convertCharset("MyModuleJavaSE/src/Reflection/ans2.txt", "gbk",
                "MyModuleJavaSE/src/Reflection/ans2-converted.txt", "utf-8");
        // gbk is the default charsetName in windows.
    }

    public static void convertCharset(String fromPath, String fromCharset, String toPath, String toCharset) throws IOException {
        FileReader fr = new FileReader(fromPath, Charset.forName(fromCharset));
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(new FileWriter(toPath, Charset.forName(toCharset)));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        fr.close();
        br.close();
    }


}
