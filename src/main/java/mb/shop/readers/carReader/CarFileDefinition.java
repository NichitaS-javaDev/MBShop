package mb.shop.readers.carReader;

public enum CarFileDefinition {
    MODEL_CODE(1,"model_code"),
    MODEL_NAME(2,"model_name"),
    TYPE_CODE(3,"type_code"),
    TYPE_NAME(4,"type_name"),
    CURRENT_PRICE(5,"current_price");

    public int index;
    public String column_name;

    CarFileDefinition(int index, String column_name) {
        this.index = index;
        this.column_name = column_name;
    }
}
