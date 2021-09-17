package mb.shop.dataCaches;

import mb.shop.entities.Car;
import mb.shop.readers.carReader.CarFileReader;
import java.util.*;

public class CarsCache {
    public static HashMap<String, Car> carMap = new HashMap<>();

    public void init(){
        new CarFileReader().loadData();
    }

    public HashMap<String,Car> addCar(Car car){
        carMap.put(generateKey(String.valueOf(car.model.code),String.valueOf(car.type.code)), car);

        return carMap;
    }

    public Car getCar(String model, String type){
        return carMap.get(generateKey(model,type));
    }

    private String generateKey(String modelCode, String typeCode){
        return modelCode + typeCode;
    }
}
