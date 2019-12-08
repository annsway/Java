/**Smiley 2 Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Color;

public class Smiley extends JPanel {
    int headSize, locX, locY;
    Graphics2D g2d; // make it global so that all the methods can have access to it
    Color faceColor, eyeColor;
    Boolean isWink = false, isSad=false, isUpSideDown=false, isLaugh=false, drawNose=false;

    public Smiley (int x, int y, int size, Color headColor, Color eColor) {
        locX = x;
        locY = y;
        headSize = size;
        faceColor = headColor;
        eyeColor = eColor;
    }

    // extends JPanel -> call paint()

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // call drawSmiley()
        drawSmiley(g);
    }

    // method 1
    public void wink(){
        isWink = true;
        isSad = false;
        isLaugh = false;
        isUpSideDown = false;
    }


    // method 2
    public void sad(){
        isSad = true;
        isLaugh = false;
        isUpSideDown = false;
        isWink = false;
    }

    // method 3
    public void upsidedown(){
        isUpSideDown = true;
        isSad = false;
        isLaugh = false;
        isWink = false;

    }

    // method 4
    public void laugh(){
        isLaugh = true;
        isSad = false;
        isWink = false;
        isUpSideDown = false;

    }

    public void setNose(boolean hasNose){
        drawNose = hasNose;
    }

    public void setHeadSize(int size){
        headSize=headSize+size;
    }


    public void setFaceColor (Color color) {
        faceColor = color;
    }

    public void setEyeColor (Color color) {
        eyeColor = color;
    }


    public void drawSmiley(Graphics g) {

        // draw head
        g.setColor(faceColor);
        g.fillOval(locX, locY, headSize, headSize);

        // draw eyes
        g.setColor(eyeColor);

        if (isWink) {
            g.fillOval((int)(locX + headSize *.25), (int)(locY + headSize *.25),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9));
            g.drawArc((int)(locX+ headSize *.60), (int)(locY + headSize *.25), (int)(headSize *.1), (int)(headSize *.1), 0, 180);
            // draw mouth
            g.drawArc((int)(locX+headSize*.25), (int)(locY+headSize*.3), (int)(headSize*.5), (int)(headSize*.5), 0, -180);

        }
        else if (isSad) {
            g.drawArc((int)(locX+ headSize *.60), (int)(locY + headSize *.25), (int)(headSize *.1), (int)(headSize *.1), 0, -180);
            g.drawArc((int)(locX + headSize *.25), (int)(locY + headSize *.25),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9), 0 , -180);
            // draw mouth
            g.drawLine((int)(locX+headSize*0.25), (int)(locY+headSize*0.65), (int)(locX+headSize*0.75), (int)(locY+headSize*0.65));
        }

        else if (isUpSideDown) {
            g.fillOval((int)(locX + headSize *.25), (int)(locY + headSize *.65),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9));
            g.fillOval((int)(locX + headSize *.60), (int)(locY + headSize *.65),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9));
            // draw mouth
            g.drawArc((int)(locX+headSize*.25), (int)(locY+headSize*.25), (int)(headSize*.5), (int)(headSize*.5), 0, 180);

        }

        else if (isLaugh) {
            g.drawArc((int)(locX+ headSize *.60), (int)(locY + headSize *.25), (int)(headSize *.1), (int)(headSize *.1), 0, 180);
            g.drawArc((int)(locX + headSize *.25), (int)(locY + headSize *.25),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9), 0 , 180);
            // draw mouth
            g.drawArc((int)(locX+headSize*.25), (int)(locY+headSize*.3), (int)(headSize*.5), (int)(headSize*.5), 0, -180);

        }
        else {
            g.fillOval((int)(locX + headSize *.25), (int)(locY + headSize *.25),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9));
            g.fillOval((int)(locX + headSize *.60), (int)(locY + headSize *.25),(int)(headSize - headSize *.9), (int)(headSize - headSize *.9));

            // draw mouth
            g.drawArc((int)(locX+headSize*.25), (int)(locY+headSize*.3), (int)(headSize*.5), (int)(headSize*.5), 0, -180);

        }

        if (drawNose) {
            g.setColor(Color.pink);
            g.fillOval((int)(locX + headSize *.45), (int)(locY + headSize *.5),(int)(headSize *.1), (int)(headSize *.1));
        }


        // draw mouth
    }



} // end of Smiley()
