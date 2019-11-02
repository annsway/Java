
/**PizzaOrder Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

public class PizzaOrder extends JFrame implements ActionListener, ItemListener {
    // declare all components
    JButton submit, clear;
    JTextArea output;
    JRadioButton large, medium, small;
    ButtonGroup sizeGroup;
    JCheckBox beef, salami, pepperoni;
    JPanel radios, button, checkBoxPanel, outputpanel, drawPanel;
    JScrollPane scroll;
    String outputText = "";
    double totalChecks;
    boolean largeChosen, mediumChosen, smallChosen;
    boolean beefChosen, salamiChosen, pepperoniChosen;
    Image img;

    public static void main(String[] args) {
        PizzaOrder frame = new PizzaOrder();
        frame.setSize(new Dimension(600, 600));
        frame.setLayout(new BorderLayout());
        frame.createGUI();
        frame.setVisible(true);
//        try {
//			TimeUnit.MILLISECONDS.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        frame.loadImage(0);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // call methods to create and add components
        setUpRadios();
        setUpChecks();
        setUpButtons();
        setUpOutput();

        // call methods to register components to listeners
        registerChecks();
        registerRadios();
        registerButtons();

        // call image
        setUpImage();
    }

    private void clearUI() {
        drawImage(0);
        output.setText("");

        beef.setSelected(false);
        salami.setSelected(false);
        pepperoni.setSelected(false);

        beefChosen = false;
        salamiChosen = false;
        pepperoniChosen = false;

        sizeGroup.clearSelection();
        smallChosen = false;
        mediumChosen = false;
        largeChosen = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            buildOutputString();
            output.setText(outputText);
            outputText = "";
        }

        else if (e.getSource() == clear) {
            clearUI();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (beef.isSelected() && salami.isSelected() && pepperoni.isSelected()) {
            drawImage(7);
        } else if (beef.isSelected() && salami.isSelected()) {
            drawImage(4);
        } else if (pepperoni.isSelected() && salami.isSelected()) {
            drawImage(6);
        } else if (pepperoni.isSelected() && beef.isSelected()) {
            drawImage(5);
        } else if (beef.isSelected()) {
            drawImage(1);
        } else if (pepperoni.isSelected()) {
            drawImage(3);
        } else if (salami.isSelected()) {
            drawImage(2);
        } else {
            drawImage(0);
        }

        // hold the event object in a variable for easy use later
        Object source = e.getSource();

        // if one of the checkboxes is deselected, do nothing
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            if (source == beef) {// check the source and see if it has been selected
                beefChosen = false;
                totalChecks = totalChecks - 1.99;
            }
            if (source == salami) {
                salamiChosen = false;
                totalChecks = totalChecks - 1.99;

            }
            if (source == pepperoni) {
                pepperoniChosen = false;
                totalChecks = totalChecks - 1.99;

            }
            if (source == large) {// check the source and see if it has been selected
                largeChosen = false;
                totalChecks = totalChecks - 12.0;

            }
            if (source == medium) {
                mediumChosen = false;
                totalChecks = totalChecks - 10.0;

            }
            if (source == small) {
                smallChosen = false;
                totalChecks = (totalChecks - 8.0);
            }
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);
            return;// do nothing a return will terminate the method
        }

        // set up radio events
        if (source == large) {
            largeChosen = true;// concatenate the current text to existing text
        } else if (source == medium) {
            mediumChosen = true;// \n is a new line character. It will make a new line
        } else if (source == small) {
            smallChosen = true;
        }

        /////////

        if (e.getSource() == large && large.isSelected()) {
            totalChecks = totalChecks + 12;
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);
        }
        if (e.getSource() == medium && medium.isSelected()) {
            totalChecks = totalChecks + 10;
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);
        }
        if (e.getSource() == small && small.isSelected()) {
            totalChecks = totalChecks + 8;
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);
        }

        // set up check box events
        if (source == beef && beef.isSelected()) {// check the source and see if it has been selected
            beefChosen = true;
            totalChecks = totalChecks + 1.99;
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);
        }
        if (source == salami && salami.isSelected()) {
            salamiChosen = true;
            totalChecks = totalChecks + 1.99;
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);
        }
        if (source == pepperoni && pepperoni.isSelected()) {
            pepperoniChosen = true;
            totalChecks = totalChecks + 1.99;
            output.setText("Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0);

//            output.setText("Subtotal: $"+ totalChecks +"\nTotal Cost (before tax): $"+ totalChecks);
        }
    }

    // GUI METHODS HERE
    public void buildOutputString() {
        if (beefChosen) {
            outputText += "Beef chosen: $1.99\n";
        }
        if (salamiChosen) {
            outputText += "Salami chosen: $1.99\n";
        }
        if (pepperoniChosen) {
            outputText += "Pepperoni chosen: $1.99\n";
        }
        if (largeChosen) {
            outputText += "Large chosen: $12.00\n";
        }
        if (mediumChosen) {
            outputText += "Medium chosen: $10.00\n";
        }
        if (smallChosen) {
            outputText += "Small chosen: $8.00\n";
        }

        outputText += "Subtotal: $" + Math.round(totalChecks * 100.0) / 100.0 + "\n";

        // totalTax = Math.round(totalChecks*0.06875*100.0)/100.0;
        outputText += "Total Tax: $" + Math.round(totalChecks * 0.06875 * 100.0) / 100.0 + "\n";

        // totalGratuity = Math.round(totalChecks*0.2*100.0)/100.0;
        outputText += "20% Gratuity: $" + Math.round(totalChecks * 0.2 * 100.0) / 100.0 + "\n";

        // totalChecks = Math.round(totalChecks*1.26875*100.0)/100.0;
        outputText += "Total Cost: $" + Math.round(totalChecks * 1.26875 * 100.0) / 100.0 + "\n";
    }

    // create and add all checkboxes
    public void setUpChecks() {
        checkBoxPanel = new JPanel(new GridLayout(3, 1));
        beef = new JCheckBox("beef");
        salami = new JCheckBox("salami");
        pepperoni = new JCheckBox("pepperoni");

        checkBoxPanel.add(beef);
        checkBoxPanel.add(salami);
        checkBoxPanel.add(pepperoni);

        getContentPane().add(checkBoxPanel, BorderLayout.WEST);
    }

    // register all checkboxes
    public void registerChecks() {
        beef.addItemListener(this);
        salami.addItemListener(this);
        pepperoni.addItemListener(this);
    }

    // create and all all radio buttons
    // radio buttons are added to a button group so that only one can be selected at
    // a time
    public void setUpRadios() {
        // create button group
        sizeGroup = new ButtonGroup();

        // create radio buttons
        large = new JRadioButton("large");
        medium = new JRadioButton("medium");
        small = new JRadioButton("small");

        // create the panel and set layout
        radios = new JPanel();
        radios.setLayout(new FlowLayout());

        // add buttons to the group
        sizeGroup.add(large);
        sizeGroup.add(medium);
        sizeGroup.add(small);

        // add buttons to the panel
        radios.add(large);
        radios.add(medium);
        radios.add(small);

        // add the panel to the north of the applet
        getContentPane().add(radios, BorderLayout.NORTH);
    }

    // register the radio buttons to the listener
    public void registerRadios() {
        large.addItemListener(this);
        medium.addItemListener(this);
        small.addItemListener(this);
    }

    // create and add the output text area
    public void setUpOutput() {
        // instantiate components
        outputpanel = new JPanel();
        output = new JTextArea(10, 25);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        scroll = new JScrollPane(output);// output JTextArea must get added to the JScrollPane

        // setLayout
        outputpanel.setLayout(new FlowLayout());

        // add components
        outputpanel.add(scroll);
        getContentPane().add(outputpanel, BorderLayout.CENTER);

    }

    // create and add the buttons
    public void setUpButtons() {
        // crate panel to hold buttons and set layout to flow
        button = new JPanel();
        button.setLayout(new FlowLayout());

        // create the buttons
        submit = new JButton("submit");
        clear = new JButton("clear");

        // add the buttons
        button.add(submit);
        button.add(clear);

        // add the panel
        getContentPane().add(button, BorderLayout.SOUTH);

    }

    // register the buttons to the listener
    public void registerButtons() {
        submit.addActionListener(this);
        clear.addActionListener(this);
    }

    public void setUpImage() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container ImageContainer = getContentPane(); // create a container called ImageContainer
        ImageContainer.setLayout(new FlowLayout());

        loadImage(0);

        // create components
        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null); // initialize JPanel with the image
            }
        }; // instantiate a JPanel() class into the variable drawPanel created above
        drawPanel.setPreferredSize(new Dimension(350, 350));
        drawPanel.setBackground(Color.white);
        ImageContainer.add(drawPanel);
    }

    private void loadImage(int index) {
        String[] imageFileNames = new String[] { "0_base", "1_beef", "2_salami", "3_pepperoni", "4_beef_salami",
                "5_beef_pepperoni", "6_salami_pepperoni", "7_all" };
        String path = "image/" + imageFileNames[index] + ".png";
        File myFile = new File(path);
        try {
            img = ImageIO.read(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawImage(int index) {
        loadImage(index);
        Graphics g = drawPanel.getGraphics();
        g.drawImage(img, 0, 0, null);
    }
}
