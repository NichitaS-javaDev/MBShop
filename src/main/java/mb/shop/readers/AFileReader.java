package mb.shop.readers;

import mb.shop.exceptions.InvalidFilesStructureException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AFileReader<T> {
    public String file_path;
    public int columnNum;
    public BufferedReader reader;

    public AFileReader(String file_path, int columnNum) {
        this.file_path = file_path;
        this.columnNum = columnNum;
        try {
            reader = new BufferedReader(new FileReader(file_path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    abstract public T createRecord(String row);
    abstract public boolean validateRow(String row, boolean ifHeader) throws InvalidFilesStructureException;
    abstract public boolean validateStructure(String name, int index);

    public List<T> loadData(){
        List<T> list = new ArrayList<>();
        try {
            String row = reader.readLine(); //header
            if (validateRow(row,true)){
                while (true){
                    row = reader.readLine(); //line
                    if (row != null){
                        if (validateRow(row,false)){
                            list.add(createRecord(row));
                        } else {
                            throw new InvalidFilesStructureException("Invalid line");
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException | InvalidFilesStructureException e) {
            e.printStackTrace();
        }
        return list;
    }
}
