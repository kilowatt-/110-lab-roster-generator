package view;

import model.Lab;
import presenter.LabManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class MainMenu {
    private JLabel tp_selectLab;
    private JComboBox cb_labList;
    private JButton OKButton;
    private JButton backButton;
    private JPanel jPanel;

    Lab selected;

    public MainMenu() {
        backButton.addActionListener(new BackButtonPressed());
        OKButton.addActionListener(new OKButtonPressed());
        cb_labList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selected = (Lab) cb_labList.getSelectedItem();
            }
        });

        List<Lab> l = LabManager.getLabList();

        for (Lab lab : l) {
            cb_labList.addItem(lab);
        }
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    private class BackButtonPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Start s = new Start();
            JFrame frame = Main.m;
            frame.setContentPane(s.getJPanel());
            frame.setLocationRelativeTo(null);
            frame.pack();
        }
    }

    private class OKButtonPressed implements ActionListener {


        public OKButtonPressed() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = Main.m;
            GeneratorUI generatorUI = new GeneratorUI(selected);
            frame.setContentPane(generatorUI.getJPanel());
            frame.pack();
        }
    }
}

