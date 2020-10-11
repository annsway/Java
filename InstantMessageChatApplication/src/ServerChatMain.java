import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class ServerChatMain extends JFrame implements ActionListener, KeyListener {
    private JTextArea jta;
    private JScrollPane jsp;
    private JPanel jp;
    private JTextField jtf;
    private JButton jb;
    // output
    private BufferedWriter bw = null;
    private static int serverPort;

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("chat.properties"));
            serverPort = Integer.parseInt(prop.getProperty("serverPort"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Initialize
        new ServerChatMain();
    }

    public ServerChatMain() {
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

        this.setTitle("IM Chat Server");
        this.setSize(300, 300);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /************************ TCP Server Start **************************/
        jb.addActionListener(this);
        jtf.addKeyListener(this); // to control "enter" key

        try {
            // 1. Create a socket for server
            ServerSocket serverSocket = new ServerSocket(serverPort);

            // 2. Waiting for the connection of client
            Socket socket = serverSocket.accept();

            // 3. Get the input
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in)); // read line by line

            // 4. Get the output
            OutputStream out = socket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(out));

            String line = null;
            while ((line = br.readLine()) != null) {
                jta.append(line + System.lineSeparator());
            }

            // 5. Close server socket channel
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /************************ TCP Server End **************************/

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
        text = "Server sent to client: " + text;
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
