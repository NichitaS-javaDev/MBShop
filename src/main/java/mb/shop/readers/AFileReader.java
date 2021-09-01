package mb.shop.readers;

import mb.shop.app.Car;
import mb.shop.readers.carReader.CarFileReader;
import mb.shop.app.InvalidFilesStructureException;

import java.io.IOException;
import java.util.HashMap;

public abstract class AFileReader<T> {
    public static String file_path;
    public static byte column_num;
    public static HashMap<String, Car> car_map;

    public AFileReader(String file_path, byte column_num) {
        AFileReader.file_path = file_path;
        AFileReader.column_num = column_num;
    }

    public AFileReader() {
    }

    abstract public boolean validateHeader(String row);
    abstract public boolean validateRow(String row);

    public abstract void createCar(String row);

    public HashMap loadData(){
        car_map = new HashMap<>();
        CarFileReader<Car> carFileReader = new CarFileReader<>();

        try {
            String row = carFileReader.reader.readLine(); //header
            if (carFileReader.validateHeader(row)){
                while (true){
                    row = carFileReader.reader.readLine(); //line
                    if (row != null){
                        if (carFileReader.validateRow(row)){
                            carFileReader.createCar(row);
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
//        for (Car c : car_map.values()) {
//            System.out.println(c.model);
//            System.out.println(c.type);
//            System.out.println(c.current_price);
//            System.out.println(c.img_path);
//        }
        return car_map;
    }

}
