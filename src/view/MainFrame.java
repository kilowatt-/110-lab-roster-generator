package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Lab Roster Generator");
        Start s = new Start();
        setContentPane(s.getJPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
