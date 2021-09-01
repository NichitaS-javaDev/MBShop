package mb.shop.readers.carReader;

import mb.shop.app.InvalidFilesStructureException;
import mb.shop.readers.AFileReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CarFileReader<Car> extends AFileReader {
    public BufferedReader reader;
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
    public boolean validateHeader(String row) { // package cache(pack)
        try {
            if (row.split(",").length != column_num){
                throw new InvalidFilesStructureException("Invalid File Structure");
            }
            String[] data = row.split(",");
            for (int i=0;i<data.length;i++){
                if (!validateStructure(data[i],i+1)){
                    throw new InvalidFilesStructureException("Invalid File Structure");
                }
            }
        } catch (InvalidFilesStructureException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean validateRow(String row){
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
    public void createCar(String row) {
        mb.shop.app.Car car = new mb.shop.app.Car();

        String[] s = row.split(",");

        car_map.put(s[0]+s[2],new mb.shop.app.Car(
                car.getCarByModel(s[1]),
                car.getCarByType(s[3]),Double.parseDouble(s[4].substring(1,s[4].length()-1))
        ));
        addImagePath(s[0]+s[2]);
    }

    public void addImagePath(String key){
        mb.shop.app.Car car = car_map.get(key);
        String img_name = (car.model + "_" + car.type.name)
                .toLowerCase()
                .replaceAll("_class","");

        String file_path = "src/main/resources/img/" + img_name + ".png";
        if (Files.exists(Paths.get(file_path))){
            car.img_path = file_path;
        }
    }

    boolean validateStructure(String name, int index){
        for (CarFileDefinition cfd : CarFileDefinition.values()){
            if (cfd.column_name.trim().equalsIgnoreCase(name.trim()) && cfd.index == index){
                return true;
            }
        }
        return false;
    }
}
