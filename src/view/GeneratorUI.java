package view;

import model.Lab;
import model.NonExistentLabException;
import model.Student;
import presenter.Exporter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorUI {
    private JLabel tp_lab;
    private JTable tb_labStudents;
    private JButton backButton;
    private JButton generateButton;
    private JPanel jPanel;
    private JLabel lb_progress;
    Lab selected;
    private String path;

    public GeneratorUI(Lab l) {
        this.selected = l;

        if (selected.getLabID()!="")
            tp_lab.setText("Lab " + selected.getLabID());

        else
            tp_lab.setText("Students with no assigned lab");

        String column_names[]= {"Last Name","First Name","Student Number"};

        String data[][] = getData();

        DefaultTableModel model = new DefaultTableModel(data, column_names) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        tb_labStudents.setModel(model);

        backButton.addActionListener(new BackButtonPressed());

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TANamesDialog dialog = new TANamesDialog(selected.getLabID());

                int res = dialog.showDialog();

                if (res == 0) {

                    String[] taNames = dialog.getNames();

                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fc.setDialogTitle("Select directory");

                    int i = fc.showOpenDialog(jPanel);

                    if (i == JFileChooser.APPROVE_OPTION) {
                        path = fc.getSelectedFile().getPath();

                        if (selected.getLabID() != "")
                            path = path + "/" + selected.getLabID() + ".xls";
                        else
                            path = path + "/" + "noLab.xls";

                        lb_progress.setForeground(Color.BLACK);
                        lb_progress.setText("Generating roster...");

                        SwingWorker<Void, Void> w = new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {

                                try {
                                    Exporter.export(path, selected.getLabID(), taNames);
                                } catch (NonExistentLabException e) {
                                    SwingUtilities.invokeLater(() -> {
                                        lb_progress.setForeground(Color.RED);
                                        lb_progress.setText("Selected non-existent lab");
                                    });
                                } catch (Exception e) {
                                    SwingUtilities.invokeLater(() -> {
                                        lb_progress.setForeground(Color.RED);
                                        lb_progress.setText("Error occured");
                                        e.printStackTrace();
                                    });
                                }

                                SwingUtilities.invokeLater(() -> {
                                    lb_progress.setForeground(Color.BLUE);
                                    lb_progress.setText("Done generating roster!");
                                });

                                File file = new File(path);

                                if (!Desktop.isDesktopSupported()) {
                                    System.out.println("Desktop is not supported");
                                } else {
                                    Desktop desktop = Desktop.getDesktop();
                                    if (file.exists()) desktop.open(file);
                                }

                                return null;
                            }
                        };

                        w.execute();

                    }
                    ;
                }
            }
        });
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public String[][] getData() {
        List<Student> studentList = selected.getStudents();

        String[][] array = new String[studentList.size()][3];

        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);

            array[i][0] = s.getLname();
            array[i][1] = s.getFname();
            array[i][2] = s.getSID();
        }

        return array;
    }

    private class BackButtonPressed implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame f = Main.m;
            f.setContentPane(new MainMenu().getJPanel());
            f.pack();
        }
    }
}
