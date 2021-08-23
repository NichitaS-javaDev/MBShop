package mb.shop.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AFileReader<T> {
    static String file_path;
    static byte column_num;

    public AFileReader(String file_path, byte column_num) {
        AFileReader.file_path = file_path;
        AFileReader.column_num = column_num;
    }

    public AFileReader() {
    }

    abstract boolean validateHeader(String row);

    abstract T createRecord(String row);

    protected List<T> loadData(){
        List<T> car_list = new ArrayList<>();
        CarFileReader<Car> carFileReader = new CarFileReader<>();

        try {
            String row = carFileReader.reader.readLine(); //header
            //System.out.println("head : " + row);
            if (carFileReader.validateHeader(row)){
                while (true){
                    row = carFileReader.reader.readLine(); //line
                    //System.out.println("row : "+ row);
                    if (row != null){
                        if (carFileReader.validateHeader(row)){
                            car_list.add((T) carFileReader.createRecord(row));
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
        return car_list;
    }

}
