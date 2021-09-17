package mb.shop.readers.purchaseReader;

public enum PurchaseFileDefinition {
    FIRST_NAME(0,"first_name"),
    LAST_NAME(1,"last_name"),
    MODEL_CODE(2,"model_code"),
    TYPE_CODE(3,"type_code"),
    PRICE(4,"price"),
    DATE(5,"date");

    public int index;
    public String name;

    PurchaseFileDefinition(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
