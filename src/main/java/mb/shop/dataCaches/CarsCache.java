package mb.shop.dataCaches;

import mb.shop.app.Car;
import mb.shop.app.CarModel;
import mb.shop.app.CarType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CarsCache {
    public static HashMap<String, Car> carMap = new HashMap<>();

    public HashMap<String,Car> addCar(Car car){
        carMap.put(car.model.code + "" + car.type.code, car);

        return carMap;
    }

    public Car getCar(CarModel model, CarType type){

        for (Car car : carMap.values()){
            if (car.model.name.equals(model.name) && car.type.name.equals(type.name)){
                return car;
            }
        }

        return null;
    }

    public List<Car> getCars(){
        Collection<Car> carMapValues = carMap.values();

        return new ArrayList<>(carMapValues);
    }
}
