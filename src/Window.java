import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(int width, int height, String name, Component c){
        JFrame frame = new JFrame(name);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(c);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
    }

}
