package mb.shop.factories;

import mb.shop.readers.purchaseReader.PurchaseFileDefinition;
import mb.shop.entities.Purchase;

public class PurchaseFactory {

    public Purchase createPurchase(String[] splitedRow){
        return new Purchase(
                splitedRow[PurchaseFileDefinition.FIRST_NAME.index],splitedRow[PurchaseFileDefinition.LAST_NAME.index],
                splitedRow[PurchaseFileDefinition.MODEL_CODE.index],splitedRow[PurchaseFileDefinition.TYPE_CODE.index],
                splitedRow[PurchaseFileDefinition.PRICE.index],splitedRow[PurchaseFileDefinition.DATE.index]);
    }
}
