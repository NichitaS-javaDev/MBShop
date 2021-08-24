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

    abstract T createCar(String row);

    protected List<T> loadData(){
        List<T> car_list = new ArrayList<>();
        CarFileReader<Car> carFileReader = new CarFileReader<>();

        try {
            String row = carFileReader.reader.readLine(); //header
            if (carFileReader.validateHeader(row)){
                while (true){
                    row = carFileReader.reader.readLine(); //line
                    if (row != null){
                        if (carFileReader.validateHeader(row)){
                            car_list.add((T) carFileReader.createCar(row));
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
