package mb.shop.readers.purchaseReader;

import mb.shop.entities.Purchase;
import mb.shop.exceptions.InvalidFilesStructureException;
import mb.shop.factories.PurchaseFactory;
import mb.shop.readers.AFileReader;

public class PurchaseHistoryFileReader extends AFileReader<Purchase> {

    public PurchaseHistoryFileReader() {
        super("src/main/resources/purchase-history.csv", 6);
    }

    @Override
    public Purchase createRecord(String row) {
        return new PurchaseFactory().createPurchase(row.split(","));
    }

    @Override
    public boolean validateRow(String row, boolean isHeader) throws InvalidFilesStructureException{
        if (row.split(",").length == columnNum){
            if (isHeader){
                String[] data = row.split(",");
                for (int i=0;i<data.length;i++) {
                    if (!validateStructure(data[i], i)) {
                        throw new InvalidFilesStructureException(">> Invalid Header Structure <<");
                    }
                }
            }
        } else {
            throw new InvalidFilesStructureException(">> Invalid File Structure <<");
        }
        return true;
    }

    @Override
    public boolean validateStructure(String name, int index){
        for (PurchaseFileDefinition pfd : PurchaseFileDefinition.values()){
            if (pfd.name.trim().equalsIgnoreCase(name.trim()) && pfd.index == index){
                return true;
            }
        }
        return false;
    }
}
