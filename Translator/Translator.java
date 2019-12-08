/**Translator Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Translator extends JFrame implements ActionListener {

    JPanel guiPanel, controls;
    private JTextField inputField, outputField;
    private JLabel inputLabel, outputLabel;
    private JButton translate;

    // self defined classes
    ImageArrayPanel imagePanel;
    StringArrayPanel stringPanel;

    public static void main(String[] args) {
        Translator frame = new Translator();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setUpGUI();

        frame.pack();
        frame.setVisible(true);
    }


    public void setUpGUI() {
        setLayout(new BorderLayout());
        guiPanel = new JPanel(new FlowLayout());
        // the following two must happen before setUpControls()
        setUpFields();
        setUpLabels();

        setUpControls();
        setUpImagePanel();

        add(guiPanel, BorderLayout.SOUTH);
        add(imagePanel, BorderLayout.CENTER);

    }

    public void setUpControls(){
        controls=new JPanel(new FlowLayout());
        translate=new JButton("translate");
        translate.addActionListener(this);
        controls.add(translate);

        controls.add(inputLabel);
        controls.add(inputField);
        controls.add(outputLabel);
        controls.add(outputField);

        guiPanel.add(controls);

    }

    public void setUpImagePanel(){
        imagePanel=new ImageArrayPanel();
    }


    public void setUpFields(){
        inputField = new JTextField(10);
        outputField = new JTextField(10);
    }

    public void setUpLabels(){
        inputLabel = new JLabel("Enter a word: ");
        outputLabel = new JLabel("Translation: ");

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Object translate=e.getSource();
        String input = inputField.getText();

        imagePanel.translateToImage(input);

        String output = stringPanel.translateToChinese(input);

        outputField.setText(output);

    }

} // end of Translator