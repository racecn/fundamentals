import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class Window extends Canvas {

    private static final long serialVersionUID = -8255319694373975038L;

    //creates window

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //stops game operations on window close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //puts window in center instead of top left
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.setFocusable(true);
        game.start();

        DisplayMode dm = new DisplayMode(800, 600,
                16, 75);



    }

}
