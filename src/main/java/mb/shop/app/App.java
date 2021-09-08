package mb.shop.app;

import mb.shop.readers.carReader.CarFileReader;
import mb.shop.windows.GUI;

public class App {

    public static void main(String[] args) {
        new CarFileReader<Car>("src/main/resources/cars.csv", (byte) 5).loadData();
        new GUI().createGUI();
    }
}
