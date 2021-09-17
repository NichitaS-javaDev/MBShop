package mb.shop.dataCaches;

import mb.shop.entities.Purchase;
import mb.shop.enums.CarModel;
import mb.shop.enums.CarType;
import mb.shop.readers.purchaseReader.PurchaseHistoryFileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PurchaseHistoryCache {
    public static List<Purchase> purchaseList = new ArrayList<>();

    public void init(){
        purchaseList.addAll(new PurchaseHistoryFileReader().loadData());
    }

    public void addPurchase(String first_name, String last_name, String model, String type, String price, String date){
        purchaseList.add(
                new Purchase(
                        first_name,last_name,String.valueOf(CarModel.getByModel(model).code),
                        String.valueOf(CarType.getByType(type).code),price,date
                )
        );

        FileWriter csv_writer;
        try {
            csv_writer = new FileWriter("src/main/resources/purchase-history.csv",true);

            csv_writer.append(String.join(
                    ", ", first_name, last_name, String.valueOf(Objects.requireNonNull(CarModel.getByModel(model)).code),
                    String.valueOf(Objects.requireNonNull(CarType.getByType(type)).code), price, date)
            );
            csv_writer.append("\n");
            csv_writer.flush();
            csv_writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Purchase> getPurchases(){

        Collections.reverse(purchaseList);

        return purchaseList;

    }
}
