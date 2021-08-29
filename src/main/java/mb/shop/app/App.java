package mb.shop.app;

public class App {

    public static void main(String[] args) {
        CarFileReader reader = new CarFileReader("src/main/resources/cars.csv", (byte) 5);
        reader.loadData();
        new GUI().createGUI();

        //reader.addImagePath();
        //new CarFileReader("src/main/resources/cars.csv", (byte) 5).loadData();


    }
}
