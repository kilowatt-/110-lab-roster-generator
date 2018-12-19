package presenter;

import model.WrongFormatException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class DataReader {

    public static void readData(String path) throws IOException, WrongFormatException {
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        LabManager.generateLabList(records);
    }

}
