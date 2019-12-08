/**Traffic Light Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficLight extends JFrame implements ActionListener {
    private final int DISPLAY_WIDTH = 400;
    private final int DISPLAY_HEIGHT = DISPLAY_WIDTH;
    private JPanel guiPanel;
    private DisplayPanel display;
    private JButton green, yellow, red;
    private boolean toStop, toSlow, toGo;
    private Color c;
    private int locX = 120;
    private int locY = 10;

    public static void main(String[] args) {

        TrafficLight frame = new TrafficLight();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.setUpGUI();

        frame.pack();
        frame.setVisible(true);
    }

    public void setUpGUI() {

        display = new DisplayPanel();
        guiPanel = new JPanel(new FlowLayout());
        
        JLabel label= new JLabel("Traffic Light", JLabel.CENTER);

        green = new JButton("Go");
        green.addActionListener(this);

        yellow = new JButton("Slow");
        yellow.addActionListener(this);

        red = new JButton("Stop");
        red.addActionListener(this);

        guiPanel.add(green);
        guiPanel.add(yellow);
        guiPanel.add(red);

        add(guiPanel, BorderLayout.SOUTH);
        add(display, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == green) {
            toGo = true;
            toSlow = false;
            toStop = false;
        }
        else if (e.getSource() == yellow) {
            toGo = false;
            toSlow = true;
            toStop = false;
        }
        else if (e.getSource() == red) {
            toGo = false;
            toSlow = false;
            toStop = true;
        }
        display.repaint();
    }

    class DisplayPanel extends JPanel {
        DisplayPanel() {
            setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
            this.setBackground(Color.WHITE);
        }

        public void paintComponent(Graphics g2d) {
            super.paintComponent(g2d);
            Graphics2D g = (Graphics2D) g2d;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g.setColor(Color.lightGray);
            g.fillRect(locX, locY, 150, 380);
            g.setColor(Color.black);
            g.drawRect(locX, locY, 150, 380);

            // green
            g.setColor(new Color(123, 139, 111));
            g.fillOval(locX + 35, locY + 20, 80, 80);

            // yellow
            g.setColor(new Color(216, 202, 175));
            g.fillOval(locX + 35, locY + 140, 80, 80);

            // red
            g.setColor(new Color(162, 126, 126));
            g.fillOval(locX + 35, locY + 260, 80, 80);


            if (toGo) {
                c = Color.GREEN;
                g.setColor(Color.green);
                g.fillOval(locX + 35, locY + 20, 80, 80);
            }
            else if (toSlow) {
                c = Color.YELLOW;
                g.setColor(Color.yellow);
                g.fillOval(locX + 35, locY + 140, 80, 80);
            }
            else if (toStop) {
                c = Color.RED;
                g.setColor(Color.red);
                g.fillOval(locX + 35, locY + 260, 80, 80);
            }
        }
    }
}
