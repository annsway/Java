import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OrderPanel extends JPanel implements ActionListener, ItemListener {

    private String[] dishNames = {"Baby Back Ribs"
            ,"Rib Combo"
            ,"St Louis Ribs"
            ,"Broccoli & Baked Potato"
            ,"Dave's Winning Chili"
            ,"Georgia Pork Bowl"
            ,"Smoked Jalapeno Sausage"
            ,"Ultimate Burger"
    };
    private String[] dishImagePath = {"1_baby_back_ribs.jpg"
                            , "1_rib_combo.jpg"
                            , "1_st_louis_ribs.jpg"
                            , "2_broccoli_n_potato.jpg"
                            , "2_dave_chili_soup.jpg"
                            , "2_georgia_salad.jpg"
                            , "3_smoked_jalapeno_sausage.jpg"
                            , "3_ultimate_burger.jpg"
    };
    private String[] dishDesc = {"$16.99 | 960-1750 Calories\n\n2 slow-smoked options: Original style - Dave's own rib rub and Sweet & Zesty® sauce or Memphis-Style - rubbed with a secret recipe of herbs and spices, hit with a vinegar mop and served naked.\n"
            , "$23.99 | 590-930 Calories\n\nYour choice of Burnt Ends or Burnt Buttz paired with 4 bones of St. Louis-Style Spareribs.\n"
            , "$25.99 | 1880 Calories\n\nHand-rubbed with Dave's secret blend of spices and pit-smoked for 3 - 4 hours over a smoldering hickory fire. Then slathered with Rich & Sassy® over an open flame to seal in the Famous flavor with a crispy, caramelized coating.\n"
            , "$8.29 | 760 Calories\n\nTender, fresh broccoli, smoked cheddar cheese sauce, bacon, sour cream and whipped butter. Served with choice of 1 side and a Corn Bread Muffin.\n"
            , "$4.99 | 380-490 Calories\n\nScratch-made with hot link sausage, hamburger, chili beans, onions, chipotle peppers, signature spices and a touch of Rich & Sassy® BBQ sauce.\n"
            , "$9.99 | 1280 Calories\n\nGeorgia Pork atop Down-N-Dirty Rice, red cabbage coleslaw, fresh green onions and Georgia Mustard. Served with a Corn Bread Muffin.\n"
            , "$9.49 | 1490 Calories\n\nHouse-smoked sausage and cheddar cheese platter, served with crackers and spicy Hell-Fire Pickles.\n"
            , "$10.99 | 1020-1060 Calories\n\nPiled high with Georgia Chopped Pork, bacon, sharp American cheese and our signature Beam & Cola BBQ sauce.\n"
    };

    private double[] dishPrices = {16.99, 23.99, 25.99, 8.29, 4.99, 9.99, 9.49, 10.99};

    private DishPanel[] menu; // initialize a class array of DishPanel
    private CustomerInfo customerInfoPanel;
    private JPanel menuPanel;
    private JPanel userPanel;
    private JButton buttonSubmit, buttonClearAll, buttonClearOrder;
    private JTextArea receipt, waitTimeTextArea;
    private ButtonGroup optionGroup;
    private JRadioButton toGo, dineIn;

    // the following variables are global in this class and are used in more than one method, otherwise they should be declared locally within the corresponding methods
    private String receiptText="", waitTimeText="", inputFirstName, inputLastName
            , inputPhoneNumber, inputTip, inputPhoneNumber_formatted;
    private boolean toGoChosen, dineInChosen, hasTipError=false, hasPhoneError=false
            , hasFNameError=false, hasLNameError=false, hasSelectionError=false;
    private double totalPrice = 0.0, tip = 0.0;
    private int currentNo;

    // Setup Order Panel GUI
    public OrderPanel(){
        // create two panels to arrange GUI layout: left side for menu, and right side for user input
        setUpMenuPanel();
        setUpUserPanel();
        setLayout(new GridLayout(1, 2));
    }

    private void setUpMenuPanel(){
        menuPanel = new JPanel();
        setUpMenu();
        registerCheckBoxes();
        menuPanel.setLayout(new GridLayout(4,2));
        add(menuPanel);
    }

    private void setUpUserPanel(){
        userPanel = new JPanel();
        setUpCustomerInput();
        setUpReceiptPanel();
        setUpRadios();
        setUpWaitTime();
        setUpButtons();
        registerRadios();
        userPanel.setLayout(new GridLayout(3, 2));
        add(userPanel);

    }

    private void setUpMenu(){
        menu = new DishPanel[dishNames.length]; // allocate memory for array
        for(int i=0; i<dishNames.length;i++){
            // pass parameters into the DishPanel class and create dish objects 
            menu[i] = new DishPanel(dishNames[i], dishPrices[i],dishImagePath[i], dishDesc[i]);
            menuPanel.add(menu[i]);
        }
    }

    private void setUpCustomerInput(){
        customerInfoPanel = new CustomerInfo();
        customerInfoPanel.setLayout(new GridLayout(6,2));
        userPanel.add(customerInfoPanel, BorderLayout.SOUTH);
    }

    public void getTextFromFields(){
        inputFirstName = customerInfoPanel.firstNameField.getText();
        inputLastName = customerInfoPanel.lastNameField.getText();
        inputPhoneNumber = customerInfoPanel.phoneField.getText();
        inputTip = customerInfoPanel.tipField.getText();
    }

    private void setUpReceiptPanel() {
        // instantiate components
        JPanel receiptPanel = new JPanel();
        receipt = new JTextArea(15, 15);
        JLabel receiptLabel = new JLabel("Your Receipt: ");
        receipt.setLineWrap(true);
        receipt.setWrapStyleWord(true);

        JScrollPane scrollReceipt = new JScrollPane(receipt);// receipt JTextArea must get added to the JScrollPane
        receiptPanel.setLayout(new BorderLayout());

        // add components
        receiptPanel.add(receiptLabel);
        receiptPanel.add(scrollReceipt);
        userPanel.add(receiptPanel);

    }

    private void setUpReceipt(){

        receipt.setForeground(Color.BLACK);
        receiptText = "Name: " + inputFirstName + " " + inputLastName + "\n"
                + "Phone Number: " + inputPhoneNumber + "\n";

        for (int i=0; i<menu.length; i++){
            if (menu[i].checkBox.isSelected()){
                receiptText += dishNames[i] + " chosen: $" + menu[i].getPrice() + "\n";
            }
        }
        receiptText += "Subtotal: $" + Math.round(totalPrice * 100.0) / 100.0 + "\n";

        double taxRate = 0.06875;
        double tax = Math.round(totalPrice * taxRate * 100.0) / 100.0;
        receiptText += "Total Tax: $" + tax + "\n";

        receiptText += "Gratuity: $" + tip + "\n";

        double orderTotal = Math.round((totalPrice + tax + tip) * 100.0) / 100.0;
        receiptText += "Order Total: $" + orderTotal + "\n";

    }

    private void setUpWaitTime(){
        JPanel waitTimePanel = new JPanel();
        currentNo = (int)(Math.random()*5);

        waitTimeTextArea = new JTextArea(5, 20);
        waitTimeTextArea.setLineWrap(true);
        waitTimeTextArea.setWrapStyleWord(true);

        waitTimeTextArea.setBackground(new Color(238, 238, 238));
        waitTimeTextArea.setForeground(Color.BLACK);
        waitTimeText = "Please select your order option to get the estimated wait time. ";

        waitTimeTextArea.setText(waitTimeText);

        waitTimePanel.add(waitTimeTextArea);

        waitTimePanel.setSize(100,200);
        waitTimePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userPanel.add(waitTimePanel);

    }


    // Validation for user input
    private void setFNameError(){
        hasFNameError = true;
        customerInfoPanel.firstNameField.setForeground(Color.red);
        customerInfoPanel.firstNameField.setText("Invalid first name. ");
    }

    private void setLNameError(){
        hasLNameError = true;
        customerInfoPanel.lastNameField.setForeground(Color.red);
        customerInfoPanel.lastNameField.setText("Invalid last name. ");
    }

    private void setPhoneError(){
        hasPhoneError = true;
        customerInfoPanel.phoneField.setForeground(Color.red);
        customerInfoPanel.phoneField.setText("Invalid phone number. ");
    }

    private void setTipError(){
        hasTipError = true;
        customerInfoPanel.tipField.setForeground(Color.red);
        customerInfoPanel.tipField.setText("Invalid number. ");
    }

    private void setUpSelectionError(){
        hasSelectionError = true;
        receipt.setForeground(Color.red);
        receiptText = "Please select your dish options. ";
    }

    // Validation for phone numbers using Regular Expression
    private static boolean validatePhoneNumber(String inputPhoneNum) {
        // Validate 6129999999
        if (inputPhoneNum.matches("\\d{10}")) return true;
        // Validate 612.999.9999 or 612-999-9999 or 612 999 9999
        else if(inputPhoneNum.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        // Validate (612) 999-9999 or (612) 999 9999 or (612) 999.9999
        else if(inputPhoneNum.matches("\\(\\d{3}\\)[\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        else return false;
    }

    private String formatPhoneNum (String inputPhoneNum) {
        // Reformat the user input phone number to XXX-XXX-XXXX that will be used in both JTextField and the Receipt Panel
        // Validate 6129999999
        if (inputPhoneNum.matches("\\d{10}")) {
            inputPhoneNumber_formatted = inputPhoneNumber.substring(0,3)+"-"
                    +inputPhoneNumber.substring(3,6)+"-"
                    +inputPhoneNumber.substring(6);
        }
        // Validate 612.999.9999 or 612-999-9999 or 612 999 9999
        else if(inputPhoneNum.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            inputPhoneNumber_formatted = inputPhoneNumber.substring(0,3)+"-"
                    +inputPhoneNumber.substring(4,7)+"-"
                    +inputPhoneNumber.substring(8);
        }
        // Validate (612) 999-9999 or (612) 999 9999 or (612) 999.9999
        else if(inputPhoneNum.matches("\\(\\d{3}\\)[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            inputPhoneNumber_formatted = inputPhoneNumber.substring(1,4)+"-"
                    +inputPhoneNumber.substring(6,9)+"-"
                    +inputPhoneNumber.substring(10);
        }
        return inputPhoneNumber_formatted;
    }

    // Set up methods for clearing
    private void clearOrder(){
        optionGroup.clearSelection();
        clearWaitTime();
    }

    private void clearAll(){
        inputFirstName = "";
        inputLastName = "";
        inputPhoneNumber = "";
        inputTip = "";
        waitTimeText = "";
        customerInfoPanel.clearCustomerGUI(); // to clear JTextField
        optionGroup.clearSelection();
    }

    private void clearWaitTime(){
        toGoChosen = false;
        dineInChosen = false;
        waitTimeTextArea.setForeground(Color.red);
        waitTimeText = "Please select your order option. ";
    }

    // Set up radios and buttons
    private void setUpRadios(){
        optionGroup = new ButtonGroup();

        toGo = new JRadioButton("To go");
        dineIn = new JRadioButton("Dine in");

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());

        optionGroup.add(toGo);
        optionGroup.add(dineIn);

        radioPanel.add(toGo);
        radioPanel.add(dineIn);

        userPanel.add(radioPanel, BorderLayout.SOUTH);

    }

    private void setUpButtons(){

        buttonSubmit = new JButton("Submit");
        buttonClearOrder = new JButton("Clear Order");
        buttonClearAll = new JButton("Clear All");

        // register buttons
        buttonSubmit.addActionListener(this);
        buttonClearOrder.addActionListener(this);
        buttonClearAll.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(buttonSubmit);
        buttonPanel.add(buttonClearOrder);
        buttonPanel.add(buttonClearAll);

        userPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    private void registerCheckBoxes(){
        for (int i=0; i<menu.length; i++){
            menu[i].checkBox.addItemListener(this);
        }
    }

    private void registerRadios(){
        toGo.addItemListener(this);
        dineIn.addItemListener(this);
    }

    // Set up actionPerformed and itemStateChanged
    @Override
    public void actionPerformed(ActionEvent e){

        hasTipError = false;
        hasPhoneError = false;
        hasFNameError = false;
        hasLNameError = false;
        hasSelectionError = false;

        if(e.getSource() == buttonSubmit){
            if (toGoChosen || dineInChosen){
                getTextFromFields();

                // validate user input for first name field
                if (inputFirstName.length()==0){
                    hasFNameError = true;
                    setFNameError();
                } else {
                    customerInfoPanel.firstNameField.setForeground(Color.BLACK);
                    hasFNameError = false;
                }

                // validate user input for last name field
                if (inputLastName.length()==0){
                    hasLNameError = true;
                    setLNameError();
                } else {
                    customerInfoPanel.lastNameField.setForeground(Color.BLACK);
                    hasLNameError = false;
                }

                // validate phone number format
                if (validatePhoneNumber(inputPhoneNumber)){
                    customerInfoPanel.phoneField.setForeground(Color.BLACK);
                    customerInfoPanel.phoneField.setText(formatPhoneNum(inputPhoneNumber));
                    inputPhoneNumber = inputPhoneNumber_formatted;
                } else {
                    hasPhoneError = true;
                    setPhoneError();
                }

                // validate tip data type
                try{
                    tip = Double.parseDouble(inputTip);
                }
                catch (Exception ex){
                    hasTipError = true;
                    setTipError();
                    return;
                }

                // validate tip amount (cannot be a negative number)
                if (tip < 0){
                    setTipError();
                } else {
                    customerInfoPanel.tipField.setForeground(Color.BLACK);
                    tip = Math.abs(tip); // to fix tip = -0
                    customerInfoPanel.tipField.setText(String.valueOf(tip));
                    receiptText += "Gratuity: $" + tip + "\n";
                }

                // validate if the user select any dish; note: totalPrice does not include Tip
                if (totalPrice > 0){
                    hasSelectionError = false;
                    receipt.setForeground(Color.BLACK);
                    receiptText = "Subtotal: $" + Math.round(totalPrice*100.0)/100.0+"\n";
                } else {
                    hasSelectionError = true;
                    setUpSelectionError();
                }

                // overall error check
                if (hasPhoneError || hasTipError || hasFNameError || hasLNameError){
                    receipt.setForeground(Color.red);
                    receiptText="Please enter valid value(s). ";
                    receipt.setText(receiptText);
                } else if (hasSelectionError){
                    receiptText="Please select your dish(es). ";
                    receipt.setText(receiptText);
                } else {
                    receipt.setForeground(Color.BLACK);
                    setUpReceipt();
                    receipt.setText(receiptText); //print receipt text
                    receiptText=""; // reset receipt text
                }
            }
            else {
                clearWaitTime();
            }

        }
        else if(e.getSource() == buttonClearAll) {
            clearAll();
            for (int i=0; i<menu.length; i++){
                menu[i].checkBox.setSelected(false);
            }
            receipt.setText(receiptText);
            clearWaitTime();
        } else if(e.getSource() == buttonClearOrder) {
            clearOrder();
            for (int i=0; i<menu.length; i++){
                menu[i].checkBox.setSelected(false);
            }
            receipt.setText(receiptText);
            clearWaitTime();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e){

        Object source = e.getSource();

        for (int i=0; i<menu.length; i++){
            if(e.getStateChange() == ItemEvent.SELECTED && source == menu[i].checkBox){
                menu[i].checkBox.isSelected();
                totalPrice += menu[i].getPrice();
            } else if (e.getStateChange() == ItemEvent.DESELECTED && source == menu[i].checkBox){
                totalPrice -= menu[i].getPrice();
                menu[i].checkBox.setSelected(false);
            }
        }

        receiptText = "Subtotal: $" + Math.round(totalPrice * 100.0) / 100.0 + "\n";
        receipt.setText(receiptText);

        int waitTime;
        if (source == toGo && toGo.isSelected()){
            toGoChosen = true;
            waitTime = 5+currentNo*2;
            waitTimeTextArea.setForeground(Color.BLACK);
            waitTimeText = "Your order will be ready in: "+ waitTime +" minutes. \n";

        } else if (source == dineIn && dineIn.isSelected()){
            dineInChosen = true;
            waitTime = currentNo*5;
            waitTimeTextArea.setForeground(Color.BLACK);
            waitTimeText = currentNo+" people waiting in line. \nYour estimated wait time will be "+ waitTime +" minutes. \n";

        } else if ((e.getStateChange() == ItemEvent.DESELECTED && source == dineIn)
                || (e.getStateChange() == ItemEvent.DESELECTED && source == toGo)){
            clearWaitTime();
        }

        waitTimeTextArea.setText(waitTimeText);

    }

}

