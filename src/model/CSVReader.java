package model;

import java.io.BufferedReader;
import java.io.File;

public class CSVReader {

    public static char delimiter = ',';

    public static void setDelimiter(char newDelimiter) {
        delimiter = newDelimiter;
    }

    public static CSV readCSV(String fileToCSV) {
        CSV newCSV = new CSV();

        File f = new File(fileToCSV);

        return newCSV;
    }

}
