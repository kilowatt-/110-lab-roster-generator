package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.prefs.Preferences;

public class HelpDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JCheckBox showOnStartupCheckBox;
    private JButton buttonCancel;

    public HelpDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        showOnStartupCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();

                setShowOnStartUp();
            }
        });
    }

    public void showDialog(boolean startup) {
        if (startup && !getShowOnStartup())
            return;

        showOnStartupCheckBox.setSelected(getShowOnStartup());

        try {
            this.setIconImage(ImageIO.read(getClass().getClassLoader().getResource("res/110.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTitle("Help");

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private boolean getShowOnStartup() {
        Preferences p = Preferences.userRoot().node(this.getClass().getName());

        return p.getBoolean("showOnStartup", true);

    }

    private void setShowOnStartUp() {
        Preferences p = Preferences.userRoot().node(this.getClass().getName());

        p.putBoolean("showOnStartup", showOnStartupCheckBox.isSelected());

    }

}
