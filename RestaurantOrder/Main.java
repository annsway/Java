/**Final Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

import java.awt.*;

public class Main {

    public static void main(String[] args){

        Restaurant restaurant = new Restaurant();
        restaurant.setSize(new Dimension(1300, 1000));

        restaurant.createGUI();
        restaurant.setDefaultCloseOperation(restaurant.getExitOnClose());

        restaurant.setVisible(true);
    }


}
