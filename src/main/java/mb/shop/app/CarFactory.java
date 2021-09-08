package mb.shop.app;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CarFactory {

    public Car createCar(String row){
        mb.shop.app.Car car = new mb.shop.app.Car();

        String[] splited_row = row.split(",");

        return new mb.shop.app.Car(
                car.getCarByModel(splited_row[1]),
                car.getCarByType(splited_row[3]),
                Double.parseDouble(splited_row[4].substring(1,splited_row[4].length()-1)));

    }

    public void addImagePath(Car car){
        String img_name = (car.model + "_" + car.type.name)
                .toLowerCase()
                .replaceAll("_class","");

        String file_path = "src/main/resources/img/" + img_name + ".png";
        if (Files.exists(Paths.get(file_path))){
            car.img_path = file_path;
        }
    }

}
