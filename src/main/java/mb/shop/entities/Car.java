package mb.shop.entities;

import mb.shop.enums.CarModel;
import mb.shop.enums.CarType;

import java.util.Vector;

public class Car {
    public CarModel model;
    public CarType type;
    public Double current_price;
    public String img_path;

    public Car(CarModel model, CarType type, Double current_price) {
        this.model = model;
        this.type = type;
        this.current_price = current_price;
    }

    public Car() {
    }

    public Vector<String> getModelsName(){
        Vector<String> list = new Vector<>();
        for (CarModel model : CarModel.values()) {
            list.add(model.name);
        }
        return list;
    }

    public Vector<String> getCarTypes(){
        Vector<String> list = new Vector<>();
        for (CarType type : CarType.values()) {
            list.add(type.name);
        }
        return list;
    }

    public CarModel getCarByModel(String input_model){
        for (CarModel model : CarModel.values()) {
            if (model.name.trim().equalsIgnoreCase(input_model.trim())){
                return model;
            }
        }
        return null;
    }

    public CarType getCarByType(String input_type){
        for (CarType type : CarType.values()) {
            if (type.name.trim().equalsIgnoreCase(input_type.trim())){
                return type;
            }
        }
        return null;
    }
}
