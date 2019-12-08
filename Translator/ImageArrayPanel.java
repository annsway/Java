/**Translator Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageArrayPanel extends JPanel {


    private static String[] englishDict = {"rat", "ox", "tiger", "rabbit"
            , "dragon", "snake", "horse", "goat"
            , "monkey", "rooster", "dog", "pig"};

    private static String[] imageDict = {"1-rat.png", "2-ox.png", "3-tiger.png", "4-rabbit.png"
            , "5-dragon.png", "6-snake.png", "7-horse.png", "8-goat.png"
            , "9-monkey.png", "10-rooster.png", "11-dog.png", "12-pig.png"};

    final int NUM_PICS=englishDict.length;

    JPanel imagePanel;

    ImageIcon icon;

    JLabel imageLabel;

    Image currentImage;
    Image[] pics;

    public ImageArrayPanel() {
        loadAllImages();
        setUpImagePanel();
    }

    public void loadAllImages(){
        pics=new Image[NUM_PICS];
        for(int i=0; i<NUM_PICS; i++){
            loadImage(imageDict[i], i);
        }
    }

    public void loadImage(String name, int index){
        String path = "images/"+name;
        File file = new File(path);
        try {
            pics[index] = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUpImagePanel() {
        imagePanel=new JPanel(new FlowLayout());
        imageLabel=new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        currentImage=pics[0];

        icon=new ImageIcon();
        icon.setImage(currentImage);
        imageLabel.setIcon(icon);

        imagePanel.add(imageLabel);

        add(imagePanel);

    }

    public void translateToImage(String englishWord){
        int found = imageSearch(englishWord, englishDict);
        if(found>=0) {
            currentImage = pics[found];
        }
        else {
            String path = "images/999-image-not-found.png";
            File file = new File(path);
            try {
                currentImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        icon.setImage(currentImage);
        repaint();

    }

    // search image within image array
    public static int imageSearch(String target, String[] arr){
        int count=0;
        int foundIndex=-1;
        while(count<arr.length&&foundIndex==-1){
            if(arr[count].equalsIgnoreCase(target)){
                foundIndex=count;
            }
            count++; // if image not found, then count + 1
        }
        return foundIndex;
    }
} // end of ImageArrayPanel