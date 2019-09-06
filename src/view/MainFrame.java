package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Lab Roster Generator");
        Start s = new Start();
        setContentPane(s.getJPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            this.setIconImage(ImageIO.read(getClass().getClassLoader().getResource("res/110.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTitle("CPSC 110 Lab Roster Generator");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
