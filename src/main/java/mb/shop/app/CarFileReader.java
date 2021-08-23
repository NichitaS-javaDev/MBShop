package mb.shop.app;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class CarFileReader<Car> extends AFileReader{
    BufferedReader reader;
    {
        try {
            reader = new BufferedReader(new FileReader(file_path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CarFileReader(String filePath, byte column_num) {
        super(filePath, column_num);
    }

    public CarFileReader() {
    }

    @Override
    boolean validateHeader(String row) {
        try {
            if (row.split(",").length != column_num){
                throw new InvalidFilesStructureException("Invalid File Structure");
            }
        } catch (InvalidFilesStructureException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    ArrayList<String> createRecord(String row) {
        ArrayList<String> record = new ArrayList<>();
        Collections.addAll(record,row.split(","));
        return record;
    }
}
