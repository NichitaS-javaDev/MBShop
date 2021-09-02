package mb.shop.dataCaches;

import mb.shop.app.Purchase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PurchaseHistoryCache {
    HashMap<Integer,Purchase> purchase_map = new HashMap<>();
    ArrayList<Purchase> purchase_list;

    public void addPurchase(){
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

    public ArrayList<Purchase> getPurchases(){
        addPurchase();

        Collection<Purchase> values = purchase_map.values();

        purchase_list = new ArrayList<>(values);

        Collections.reverse(purchase_list);

        return purchase_list;

    }

}
