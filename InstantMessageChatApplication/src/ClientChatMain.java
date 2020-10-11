import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class ClientChatMain extends JFrame implements ActionListener, KeyListener {
    private JTextArea jta;
    private JScrollPane jsp;
    private JPanel jp;
    private JTextField jtf;
    private JButton jb;
    private BufferedWriter bw = null;
    private static String clientIp;
    private static int clientPort;

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("chat.properties"));
            clientIp = prop.getProperty("clientIp");
            clientPort = Integer.parseInt(prop.getProperty("clientPort"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Initialize
        new ClientChatMain();
    }

    public ClientChatMain() {
        jta = new JTextArea();
        jta.setEditable(false);
        jsp = new JScrollPane(jta);
        jp = new JPanel();
        jtf = new JTextField(10);
        jb = new JButton("Send");
        jp.add(jtf);
        jp.add(jb);

        // must extends JFrame first
        this.add(jsp, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);

        this.setTitle("IM Chat Client");
        this.setSize(300, 300);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /************************ TCP Client Start **************************/
        jb.addActionListener(this);
        jtf.addKeyListener(this); // to control "enter" key

        try {
            // 1. Create a socket for client (trying to connect)
            Socket socket = new Socket(clientIp, clientPort);

            // 2. Get the input
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3. Get the output
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;
            while ((line = br.readLine()) != null) {
                jta.append(line + System.lineSeparator());
            }

            // 4. Close socket channel
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /************************ TCP Client End **************************/

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sendDataToSocket();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            sendDataToSocket();
        }
    }

    public void sendDataToSocket() {
        String text = jtf.getText();
        text = "Client sent to server: " + text;
        jta.append(text + System.lineSeparator());
        try {
            bw.write(text);
            bw.newLine();
            bw.flush();
            jtf.setText("");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
