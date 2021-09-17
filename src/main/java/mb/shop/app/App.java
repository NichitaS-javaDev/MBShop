package mb.shop.app;

import mb.shop.dataCaches.CarsCache;
import mb.shop.dataCaches.PurchaseHistoryCache;
import mb.shop.windows.GUI;

public class App {

    public static void main(String[] args) {
        new CarsCache().init();
        new PurchaseHistoryCache().init();
        new GUI().createGUI();
    }
}
