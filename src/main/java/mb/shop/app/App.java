package mb.shop.app;

public class App {

    public static void main(String[] args) {
        //new GUI().createGUI();
        new CarFileReader("src/main/resources/cars.csv", (byte) 5).loadData();
    }

}
