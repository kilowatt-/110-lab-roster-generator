package view;

import model.WrongFormatException;
import presenter.DataReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Start {
    private JPanel pnl_start;
    private JLabel txt_label;
    private JTextField txtField_selectedFile;
    private JButton browseButton;
    private JButton okButton;
    private JLabel lb_error;

    public Start() {
        browseButton.addActionListener(new BrowseBtnClicked());
        okButton.addActionListener(new OKBtnClicked());
    }



    private class BrowseBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "CSV File", "csv");
            fc.setFileFilter(filter);

            int option = fc.showOpenDialog(pnl_start);

            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String path = selectedFile.getPath();
                txtField_selectedFile.setText(path);

            }

        }
    }

    public JPanel getJPanel() {
        return pnl_start;
    }

    private class OKBtnClicked implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            lb_error.setForeground(Color.BLACK);

            SwingWorker<Void, Void> worker  = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    String path = txtField_selectedFile.getText();

                    if (!path.equals("")) {
                        try {

                            SwingUtilities.invokeLater(() -> {
                                lb_error.setText("Reading file...");

                            });

                            DataReader.readData(path);
                            SwingUtilities.invokeLater(() -> {
                                lb_error.setText("Done");
                                MainMenu m = new MainMenu();
                                JFrame frame = Main.m;
                                frame.setContentPane(m.getJPanel());
                                frame.setLocationRelativeTo(null);
                                frame.pack();
                            });

                        } catch (WrongFormatException e1) {
                            e1.printStackTrace();
                            SwingUtilities.invokeLater(() -> {
                                lb_error.setForeground(Color.RED);
                                lb_error.setText("CSV file has wrong format");
                            });


                        } catch (IOException e1) {
                            e1.printStackTrace();
                            SwingUtilities.invokeLater(() -> {
                                lb_error.setForeground(Color.RED);
                                lb_error.setText("IO Exception occured");
                            });
                        }
                    }

                    else
                        SwingUtilities.invokeLater(() -> {
                            lb_error.setForeground(Color.RED);
                            lb_error.setText("Select a file");
                        });
                    return null;
                }
            };

            worker.execute();

        }
    }
}
