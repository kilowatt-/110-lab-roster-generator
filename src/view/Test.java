package view;

import model.NonExistentLabException;
import model.WrongFormatException;
import presenter.DataReader;
import presenter.Exporter;

import java.io.IOException;

public class Test {
    public static void main (String[] args) {
        String s = "/home/kw/grades.csv";
        String outPath = "/home/kw/l1q.xls";

        try {
            DataReader.readData(s);
            String[] taNames = {"Kean Wah Wong", "Riley Knowles", "Yexiu Zhou"};
            Exporter.export(outPath, "L1Q", taNames);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFormatException e) {
            e.printStackTrace();
        } catch (NonExistentLabException e) {
            e.printStackTrace();
        }
    }
}
