package mb.shop.factories;

import mb.shop.entities.Car;
import mb.shop.readers.carReader.CarFileDefinition;

public class CarFactory {

    public Car createCar(String[] splitedRow){
        Car car = new Car();

        return new Car(
                car.getCarByModel(splitedRow[CarFileDefinition.MODEL_CODE.index]),
                car.getCarByType(splitedRow[CarFileDefinition.TYPE_CODE.index]),
                Double.parseDouble(
                        splitedRow[CarFileDefinition.CURRENT_PRICE.index-1].substring(
                                1,splitedRow[CarFileDefinition.CURRENT_PRICE.index-1].length()-1
                        )
                ));
    }

}
