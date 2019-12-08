/**Smiley 2 Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SmileyControl extends JPanel implements ActionListener, ItemListener {
    Smiley smiley;

    JPanel controlPanel;

    JButton button_wink, button_sad, button_usd, button_laugh
            , bigger, smaller, addNose, removeNose;

    JRadioButton faceRed, faceBlue, faceGreen
            , eyeBlack, eyeGray, eyeWhite;

    ButtonGroup faceColorGroup, eyeColorGroup;

    public SmileyControl(Smiley s){
        smiley=s;
        setUpControls();
    }

    public void setUpControls() {
        controlPanel = new JPanel(new GridLayout(4, 4));

        button_wink = new JButton("wink");
        button_wink.addActionListener(this);
        controlPanel.add(button_wink);

        button_sad = new JButton("sad");
        button_sad.addActionListener(this);
        controlPanel.add(button_sad);

        button_usd = new JButton("upsidedown");
        button_usd.addActionListener(this);
        controlPanel.add(button_usd);

        button_laugh = new JButton("laugh");
        button_laugh.addActionListener(this);
        controlPanel.add(button_laugh);

        addNose = new JButton("Add nose");
        addNose.addActionListener(this);
        controlPanel.add(addNose);

        removeNose = new JButton("Remove nose");
        removeNose.addActionListener(this);
        controlPanel.add(removeNose);

        // add attributes

        bigger = new JButton("Face: bigger");
        bigger.addActionListener(this);
        controlPanel.add(bigger);

        smaller = new JButton("Face: smaller");
        smaller.addActionListener(this);
        controlPanel.add(smaller);



        // face color
        faceRed=new JRadioButton("Face color: Red");
        faceBlue=new JRadioButton("Face color: Blue");
        faceGreen=new JRadioButton("Face color: Green");
        faceRed.addItemListener(this);
        faceBlue.addItemListener(this);
        faceGreen.addItemListener(this);
        faceColorGroup= new ButtonGroup();
        faceColorGroup.add(faceRed);
        faceColorGroup.add(faceBlue);
        faceColorGroup.add(faceGreen);
        controlPanel.add(faceRed);
        controlPanel.add(faceBlue);
        controlPanel.add(faceGreen);

        // eye color
        eyeBlack=new JRadioButton("Eye color: black");
        eyeGray=new JRadioButton("Eye color: gray");
        eyeWhite=new JRadioButton("Eye color: white");
        eyeBlack.addItemListener(this);
        eyeGray.addItemListener(this);
        eyeWhite.addItemListener(this);
        eyeColorGroup= new ButtonGroup();
        eyeColorGroup.add(eyeBlack);
        eyeColorGroup.add(eyeGray);
        eyeColorGroup.add(eyeWhite);
        controlPanel.add(eyeBlack);
        controlPanel.add(eyeGray);
        controlPanel.add(eyeWhite);

        add(controlPanel, BorderLayout.CENTER);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(e.getSource()==button_wink){
            smiley.wink();
            smiley.repaint();
        }
        else if(e.getSource()==button_sad){
            smiley.sad();
            smiley.repaint();
        }
        else if(e.getSource()==button_usd){
            smiley.upsidedown();
            smiley.repaint();
        }
        else if(e.getSource()==button_laugh){
            smiley.laugh();
            smiley.repaint();
        }

        if(source==bigger){
            smiley.setHeadSize(10);
            smiley.repaint();
        }
        if(source==smaller){
            smiley.setHeadSize(-10);
            smiley.repaint();
        }
        if(source==addNose) {
            smiley.setNose(true);
            smiley.repaint();
        }

        if(source==removeNose) {
            smiley.setNose(false);
            smiley.repaint();
        }

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        // set face color
        if (e.getSource() == faceRed) {
            smiley.setFaceColor(Color.red);
            smiley.repaint();
        }
        if (e.getSource() == faceGreen) {
            smiley.setFaceColor(Color.green);
            smiley.repaint();
        }
        if (e.getSource() == faceBlue) {
            smiley.setFaceColor(Color.blue);
            smiley.repaint();
        }
        // set eye color
        if (e.getSource() == eyeBlack) {
            smiley.setEyeColor(Color.black);
            smiley.repaint();
        }
        if (e.getSource() == eyeGray) {
            smiley.setEyeColor(Color.gray);
            smiley.repaint();
        }
        if (e.getSource() == eyeWhite) {
            smiley.setEyeColor(Color.white);
            smiley.repaint();
        }
    }
}