package model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataReader {
    public static List<Lab> labList;

    public static void readData(String path) throws IOException {
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

        labList = generateLabList(records);

        for (Lab l: labList) {
            System.out.println(l.getLabID());
        }
    }

    private static List<Lab> generateLabList(Iterable<CSVRecord> records) {
        List<Lab> list = new ArrayList<>();

        for (CSVRecord r: records) {
            String labID= r.get("Lab");
            String[] name = r.get("Student").split(",");

            if (name.length != 2)
                continue;

            String sid = r.get("Student Number");


            String fname = name[1];
            String lname = name[0];


            Lab l = new Lab(labID);

            if (list.contains(l))
                l = list.get(list.indexOf(l));
            else
                list.add(l);

            Student s = new Student(fname, lname, sid);
            l.addStudent(s);
        }


        return list;
    }

}
