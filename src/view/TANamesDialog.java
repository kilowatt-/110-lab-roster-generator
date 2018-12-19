package view;

import javax.swing.*;
import java.awt.event.*;
import java.util.prefs.Preferences;

public class TANamesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tf_ta1;
    private JTextField tf_ta2;
    private JTextField tf_ta3;
    private JLabel lb_error;
    private String[] taNames;
    private String labID;
    int res;

    public TANamesDialog(String labID) {
        setContentPane(contentPane);
        this.labID = labID;
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        taNames = new String[3];

        taNames[0] = tf_ta1.getText();
        taNames[1] = tf_ta2.getText();
        taNames[2] = tf_ta3.getText();

        if (tf_ta1.getText().equals("") || tf_ta2.getText().equals("") || tf_ta3.getText().equals("")) {
            lb_error.setText("Please fill out all three TA names");
            this.pack();
            this.setVisible(true);
        }

        else {
            saveTAs(taNames);
            res = 0;
            dispose();
        }
    }

    private void onCancel() {
        res = 1;
        dispose();
    }

    int showDialog() {
        String[] tas = getSavedTAs();

        if (tas != null) {
            tf_ta1.setText(tas[0]);
            tf_ta2.setText(tas[1]);
            tf_ta3.setText(tas[2]);
        }

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        return res;
    }

    String[] getNames() {
        return taNames;
    }

    private void saveTAs(String[] tas) {
        Preferences p = Preferences.userRoot().node(this.getClass().getName());

        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < 3; i++) {
            s.append(tas[i]);

            if (i < 2)
                s.append(",");

        }

        p.put(labID, s.toString());
    }

    private String[] getSavedTAs() {
        Preferences p = Preferences.userRoot().node(this.getClass().getName());

        String s = p.get(labID, null);

        if (s != null) {
            return s.split(",");
        }

        return null;
    }




}
