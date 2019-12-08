import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Restaurant extends JFrame{

    private Image img;

    int getExitOnClose() {
        return EXIT_ON_CLOSE;
    }

    void createGUI(){
        setUpImage();
        setUpPanels();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    private void setUpPanels(){
        OrderPanel order = new OrderPanel();
        add(order);
    }


    private void setUpImage(){

        loadImage();
        // create components
        // initialize JPanel with the image
        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null); // initialize JPanel with the image
            }
        }; // instantiate a JPanel() class into the variable drawPanel created above
        drawPanel.setPreferredSize(new Dimension(1200, 300));
        drawPanel.setBackground(new Color(184, 41, 39));
        add(drawPanel, BorderLayout.SOUTH);

    }

    private void loadImage(){
        String path = "images/0_banner.jpg";
        File myFile = new File (path);
        try{
            img = ImageIO.read(myFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}



