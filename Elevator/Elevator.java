/**Elevator Project
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class Elevator extends JFrame implements ActionListener, ItemListener {

    private final int DISPLAY_WIDTH = 600;
    private final int DISPLAY_HEIGHT = 400;
    private static final int NUM_ITERATIONS = 10;

    private JPanel guiPanel;
    private DisplayPanel display;
    private JButton Start;
    JComboBox choiceBox;


    int locX = 100;//starting point
    int locY = 50;

    final int WINDOW_WIDTH = 20;
    final int WINDOW_HEIGHT = 25;
    final int FLOOR_GAP = 5;
    final int WINDOW_GAP = 5;
    final int NUM_FLOORS = 10;
    final int NUM_WINDOWS = 7;

    int currentFloor= 1;

    // Set the initial elevator to be on the first windows from left on the first floor
    int elevatorX = locX + WINDOW_GAP;
    int elevatorY = locY + (NUM_FLOORS - currentFloor) * (FLOOR_GAP + WINDOW_HEIGHT) + FLOOR_GAP; //9th

    int nextFloor;
    int floorDiff;

    public static void main(String[] args) {

        Elevator frame = new Elevator();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.createGUI();
        frame.setUpGUI();
        frame.pack();
        frame.setVisible(true);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUpGUI();

    }

    public void setUpGUI() {
        Container window = getContentPane();
        display = new DisplayPanel();
        guiPanel = new JPanel(new FlowLayout());


        // ComboBox
        Start = new JButton("Start");
        Start.addActionListener(this);
        choiceBox = new JComboBox();
        for (int i = 1; i <= NUM_ITERATIONS; i++) {
            choiceBox.addItem(String.valueOf(i));
        }
        guiPanel.add(choiceBox);
        choiceBox.addItemListener(this);

        guiPanel.add(choiceBox);
        guiPanel.add(Start);

        window.add(guiPanel, BorderLayout.NORTH);
        window.add(display, BorderLayout.CENTER);

    } // end of SetUpGUI


    public void itemStateChanged(ItemEvent e) {

        nextFloor = Integer.parseInt((String)choiceBox.getSelectedItem()); // get user input

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Start) {

            // Tips: floorDifference = currentFloor - nextFloor; // 3-10 == -7
            // (floorDifference*floorHeight)+elevatorY; // 30*(-7)+elevatorY ⇒ -210+elevatorY

            // the values will change only when user clicks Start
            floorDiff = currentFloor - nextFloor;
            currentFloor = nextFloor; // update the current floor once reached to the next floor
            elevatorY = elevatorY + floorDiff * (WINDOW_HEIGHT+FLOOR_GAP);
            display.repaint();
        }
    }

    class DisplayPanel extends JPanel {

        Graphics2D g2d;

        DisplayPanel() {
            setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
            this.setBackground(Color.blue);
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            draw();

        }

        private void drawBuilding () {
            // Draw the background of the building
            g2d.setColor(Color.lightGray);
            g2d.fillRect(locX, locY, (WINDOW_WIDTH + WINDOW_GAP) * NUM_WINDOWS + WINDOW_GAP
                    , (WINDOW_HEIGHT + FLOOR_GAP) * NUM_FLOORS + FLOOR_GAP);
        }

        private void drawGround() {
            g2d.setColor(Color.darkGray);
            g2d.fillRect(0, locY+(WINDOW_HEIGHT + FLOOR_GAP)*NUM_FLOORS
                    , getWidth(), locY+(WINDOW_HEIGHT + FLOOR_GAP)*NUM_FLOORS+5);

        }

        private void drawWindows () {
            g2d.setColor(Color.black);
            // Draw the windows of the building
            for (int j = 0; j < NUM_FLOORS; j++) {
                for (int i = 0; i < NUM_WINDOWS; i++) {
                    g2d.fillRect(locX + WINDOW_GAP + i * (WINDOW_WIDTH + WINDOW_GAP)
                            , locY + FLOOR_GAP + j * (WINDOW_HEIGHT + FLOOR_GAP)
                            , WINDOW_WIDTH, WINDOW_HEIGHT);
                }
            }
        }

        private void drawElevator () {

            g2d.setColor(Color.yellow);

            g2d.fillRect(elevatorX, elevatorY, WINDOW_WIDTH, WINDOW_HEIGHT);

        }

        private void drawMoon(){
            g2d.fillOval(400, 50, 50, 50);

        }

        private void draw () {
            drawGround();
            drawBuilding();
            drawWindows();
            drawElevator();
            drawMoon();

        }



    } // end of DisplayPanel()

}// end of Elevator



