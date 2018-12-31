package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Lab Roster Generator");
        Start s = new Start();
        setContentPane(s.getJPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/res/110.png");
        this.setIconImage(icon.getImage());
        this.setTitle("CPSC 110 Lab Roster Generator");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
