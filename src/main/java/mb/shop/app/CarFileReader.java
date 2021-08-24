package mb.shop.app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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
    HashMap<String, mb.shop.app.Car> createCar(String row) {
        mb.shop.app.Car car = new mb.shop.app.Car();
        HashMap<String, mb.shop.app.Car> car_map = new HashMap<>();
        String[] s = row.split(",");
        car_map.put(
                s[0]+s[2],
                new mb.shop.app.Car(
                        car.getCarByModel(s[1]),
                        car.getCarByType(s[3]),Double.parseDouble(s[4].substring(1,s[4].length()-1))
                )
        );

//        for (mb.shop.app.Car c: car_map.values()) {
//            System.out.println(c.model);
//            System.out.println(c.type);
//            System.out.println(c.current_price);
//        }

        return car_map;
    }
}
