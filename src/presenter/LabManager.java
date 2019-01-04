package presenter;

import model.Lab;
import model.Student;
import model.WrongFormatException;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabManager {
    private static List<Lab> labList;

    public static List<Lab> getLabList() {
        return Collections.unmodifiableList(labList);
    }

    public static Lab getLabById(String labID) {
        Lab l = new Lab(labID);

        if (labList.contains(l))
            return labList.get(labList.indexOf(l));

        return null;
    }

    static void generateLabList(Iterable<CSVRecord> records) throws WrongFormatException {
        List<Lab> list = new ArrayList<>();

        for (CSVRecord r: records) {

            try {
                String labID= r.get("Lab");

                if (labID.equals(""))
                    continue;

                String[] name = r.get("Student").split(" ", 2);

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

            catch(IllegalArgumentException e) {
                throw new WrongFormatException(e.getMessage());
            }



        }

        Collections.sort(list);

        labList = list;

    }


}
