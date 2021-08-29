package mb.shop.app;

import java.util.Vector;

public class Car {
    CarModel model;
    CarType type;
    Double current_price;
    String img_path;

    public Car(CarModel model, CarType type, Double current_price) {
        this.model = model;
        this.type = type;
        this.current_price = current_price;
    }

    public Car() {
    }

    Vector<String> getModelsName(){
        Vector<String> list = new Vector<>();
        for (CarModel model : CarModel.values()) {
            list.add(model.name);
        }
        return list;
    }

    Vector<String> getCarTypes(){
        Vector<String> list = new Vector<>();
        for (CarType type : CarType.values()) {
            list.add(type.name);
        }
        return list;
    }

    CarModel getCarByModel(String input_model){
        for (CarModel model : CarModel.values()) {
            if (model.name.trim().equalsIgnoreCase(input_model.trim())){
                return model;
            }
        }
        return null;
    }

    CarType getCarByType(String input_type){
        for (CarType type : CarType.values()) {
            if (type.name.trim().equalsIgnoreCase(input_type.trim())){
                return type;
            }
        }
        return null;
    }
}
