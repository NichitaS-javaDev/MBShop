package mb.shop.readers.carReader;

import mb.shop.factories.CarFactory;
import mb.shop.dataCaches.CarsCache;
import mb.shop.entities.Car;
import mb.shop.exceptions.InvalidFilesStructureException;
import mb.shop.readers.AFileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CarFileReader extends AFileReader<Car> {

    public CarFileReader() {
        super("src/main/resources/cars.csv",5);
    }

    @Override
    public Car createRecord(String row){
        Car car =  new CarFactory().createCar(row.split(","));
        new CarsCache().addCar(car);

        String img_name = (car.model + "_" + car.type.name)
                .toLowerCase()
                .replaceAll("_class","");

        String file_path = "src/main/resources/img/" + img_name + ".png";
        if (Files.exists(Paths.get(file_path))){
            car.img_path = file_path;
        }
        return car;
    }

    @Override
    public boolean validateRow(String row, boolean ifHeader) {
        try {
            if (row.split(",").length != columnNum){
                throw new InvalidFilesStructureException("Invalid File Structure");
            }
            if (ifHeader){
                String[] data = row.split(",");
                for (int i=0;i<data.length;i++){
                    if (!validateStructure(data[i],i+1)){
                        throw new InvalidFilesStructureException("Invalid File Structure");
                    }
                }
            }
        } catch (InvalidFilesStructureException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean validateStructure(String name, int index){
        for (CarFileDefinition cfd : CarFileDefinition.values()){
            if (cfd.column_name.trim().equalsIgnoreCase(name.trim()) && cfd.index == index){
                return true;
            }
        }
        return false;
    }
}
