/**Smiley 2 Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import java.awt.*;
import javax.swing.*;
import java.awt.Color;

public class SmileyFrame extends JFrame {

    //SETP 1! Make variables for the new smiley, controller and panel
    BorderLayout border;
    JPanel smileyAndControl1, smileyAndControl2, centerContainer;

    Smiley smiley1, smiley2;
    SmileyControl control1, control2; // calling the CircleControl class from the other tab

    //create constants to avoid magic numbers
    public static void main(String[]args){

        SmileyFrame frame=new SmileyFrame();

        frame.setSize(new Dimension(1100,900));
        frame.createGUI();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


    public void setUpSmiley(){
        smiley1 = new Smiley(150,20,100, makeRandomColor(), makeRandomColor());
        smiley2 = new Smiley(150,20,100, makeRandomColor(), makeRandomColor());

    }

    public void setUpCenter(){
        centerContainer=new JPanel(new FlowLayout());

        smileyAndControl1=new JPanel(new BorderLayout());
        smileyAndControl1.setBorder(BorderFactory.createLineBorder(Color.black));

        smileyAndControl2=new JPanel(new BorderLayout());
        smileyAndControl2.setBorder(BorderFactory.createLineBorder(Color.black));

        //STEP 2!! instantiate jpanel to hold the smiley and controller

        setUpSmiley();
        setUpControls();

        smiley1.setPreferredSize(new Dimension(200,200));
        smiley2.setPreferredSize(new Dimension(200,200));

        //step 7: set the size on the smiley control panel

        smileyAndControl1.add(smiley1, BorderLayout.CENTER);
        smileyAndControl1.add(control1, BorderLayout.SOUTH);
        smileyAndControl2.add(smiley2, BorderLayout.CENTER);
        smileyAndControl2.add(control2, BorderLayout.SOUTH);

        //step 6: add smiley and controller 3 to their panel
        centerContainer.add(smileyAndControl1);
        centerContainer.add(smileyAndControl2);

        add(centerContainer, BorderLayout.CENTER);
    }



    private void createGUI(){

        border=new BorderLayout();
        setLayout(border);

        setLayout(new BorderLayout());
        setUpControls();
        setUpSmiley();

        border=new BorderLayout();
        setLayout(border);
        //setUpTitle();
        setUpCenter();

    }


    public void setUpControls(){
        //step 5: instantiate new control object
        control1=new SmileyControl(smiley1);
        control2=new SmileyControl(smiley2);
    }

    public Color makeRandomColor() {
        int r=(int)(Math.random()*256);
        int g=(int)(Math.random()*256);
        int b=(int)(Math.random()*256);
        Color makeRandomColor=new Color(r, g, b);
        return makeRandomColor;
    }

}
