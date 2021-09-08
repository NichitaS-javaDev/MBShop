package mb.shop.dataCaches;

import mb.shop.app.Purchase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PurchaseHistoryCache {
    HashMap<Integer,Purchase> purchase_map = new HashMap<>();
    List<Purchase> purchaseList;

    public void addPurchases(){
        int key = 1;
        Purchase purchase;
        try {
            BufferedReader csv_reader = new BufferedReader(
                    new FileReader("src/main/resources/purchase-history.csv")
            );
            String row;
            csv_reader.readLine(); //reads header
            while ((row = csv_reader.readLine()) != null){
                String[] data = row.split(",");
                purchase = new Purchase(data[0],data[1],data[2],data[3],data[4],data[5]);
                purchase_map.put(key,purchase);
                key++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public List<Purchase> getPurchases(){
        addPurchases();

        Collection<Purchase> values = purchase_map.values();

        purchaseList = new ArrayList<>(values);

        Collections.reverse(purchaseList);

        return purchaseList;

    }

}
