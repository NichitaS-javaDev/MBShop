package mb.shop.readers;

import mb.shop.app.Car;
import mb.shop.app.CarFactory;
import mb.shop.dataCaches.CarsCache;
import mb.shop.readers.carReader.CarFileReader;
import mb.shop.exceptions.InvalidFilesStructureException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public abstract class AFileReader<T> {
    public static String file_path;
    public static byte column_num;
    public List<T> car_list;

    public AFileReader(String file_path, byte column_num) {
        AFileReader.file_path = file_path;
        AFileReader.column_num = column_num;
    }

    public AFileReader() {
    }

    abstract public boolean validateRow(String row, boolean ifHeader);

    public List<T> loadData(){
        //car_map = new HashMap<>();
        car_list = new Vector<>();
        CarFileReader<Car> carFileReader = new CarFileReader<>();
        CarFactory carFactory = new CarFactory();
        CarsCache carsCache = new CarsCache();

        try {
            String row = carFileReader.reader.readLine(); //header
            if (carFileReader.validateRow(row,true)){
                while (true){
                    row = carFileReader.reader.readLine(); //line
                    if (row != null){
                        if (carFileReader.validateRow(row,false)){
                            Car car = carFactory.createCar(row);
                            car_list.add((T) car);
                            carsCache.addCar(car);
                            carFactory.addImagePath(car);
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
