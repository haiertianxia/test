package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


/**
 * Created by x on 2017/3/9.
 * 从excel 读取数据
 */
public class CsvUtility {

    public Iterable<CSVRecord> readCsvFile(String csvPath) {
        Reader reader = null;
        try {
            reader = new FileReader(csvPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Iterable<CSVRecord> records = null;
        if (reader != null) {
            try {
                records = CSVFormat.EXCEL.parse(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return records;
    }


}
