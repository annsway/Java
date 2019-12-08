import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class DishPanel extends JPanel {

    private double price; // price should be private so that other developers (if any) cannot change it, but to access it by calling getPrice() method
    JCheckBox checkBox;
    private Image img;

    DishPanel(String name, double price, String imagePath, String desc){

        // pass the parameters into the following methods
        setUpCheckBox(name);
        setUpImage(imagePath);
        setUpDishPanel(desc);
        setLayout(new GridLayout(1,1));
        this.price = price; // this. ... is used for distinguishing the price of the class from the pass-in price
    }

    private void setUpCheckBox(String name){
        checkBox = new JCheckBox(name);
        Font fontCheckBox = new Font("Helvetica", Font.BOLD, 11);
        checkBox.setFont(fontCheckBox);

    }

    private void setUpDishPanel(String description){
        JPanel dishPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea descTextArea = new JTextArea(11,18);
        Font fontDesc = new Font("Helvetica", Font.PLAIN, 10);

        descTextArea.setText(description);
        descTextArea.setLineWrap(true);
        descTextArea.setWrapStyleWord(true);
        descTextArea.setFont(fontDesc);
        descTextArea.setForeground(Color.darkGray);
        descTextArea.setBackground(new Color(238, 238, 238));

        dishPanel.add(checkBox);
        dishPanel.add(descTextArea);

        add(dishPanel);
    }


    double getPrice(){
        return this.price;
        // This method must be public so that other classes can call it
    }

    
    private void setUpImage(String imgPath){
        File imageFile = new File("images/"+imgPath);

        try {
            img = ImageIO.read(imageFile);
        } catch(IOException e){
            e.printStackTrace();
        }

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 5, null);
            }
        };

        imagePanel.setPreferredSize(new Dimension(300,300));
        imagePanel.setBackground(new Color(238, 238, 238));
        add(imagePanel);

    }

}
