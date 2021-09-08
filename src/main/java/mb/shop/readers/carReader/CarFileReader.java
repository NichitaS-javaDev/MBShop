package mb.shop.readers.carReader;

import mb.shop.exceptions.InvalidFilesStructureException;
import mb.shop.readers.AFileReader;
import java.io.*;

public class CarFileReader<Car> extends AFileReader {
    public BufferedReader reader;
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
    public boolean validateRow(String row, boolean ifHeader) {
        try {
            if (row.split(",").length != column_num){
                throw new InvalidFilesStructureException("Invalid File Structure");
            }
            if (ifHeader){
                String[] data = row.split(",");
                for (int i=0;i<data.length;i++){
                    if (!validateStructure(data[i],i+1)){
                        throw new InvalidFilesStructureException("Invalid File Structure");
                    }
                }
            }
        } catch (InvalidFilesStructureException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean validateStructure(String name, int index){
        for (CarFileDefinition cfd : CarFileDefinition.values()){
            if (cfd.column_name.trim().equalsIgnoreCase(name.trim()) && cfd.index == index){
                return true;
            }
        }
        return false;
    }
}
