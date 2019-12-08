import javax.swing.*;
import java.awt.*;

class CustomerInfo extends JPanel {
    private JLabel firstNameLabel, lastNameLabel, phoneLabel, tipLabel;
    JTextField firstNameField, lastNameField, phoneField, tipField;

    CustomerInfo(){

        setLayout(new GridLayout(6, 2));
        setUpFields();
        setUpLabels();
        createGUI();

    }

    private void createGUI(){

        add(firstNameLabel);
        add(firstNameField);

        add(lastNameLabel);
        add(lastNameField);

        add(phoneLabel);
        add(phoneField);

        add(tipLabel);
        add(tipField);

    }

    void clearCustomerGUI(){
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        tipField.setText("");
    }

    private void setUpFields(){
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        phoneField = new JTextField(15);
        tipField = new JTextField(15);
    }

    private void setUpLabels(){
        firstNameLabel = new JLabel("First Name: ", JLabel.RIGHT);
        lastNameLabel = new JLabel("Last Name: ", JLabel.RIGHT);
        phoneLabel = new JLabel("Phone Number: ", JLabel.RIGHT);
        tipLabel = new JLabel("Tip (>= $0): ", JLabel.RIGHT);
    }

}

