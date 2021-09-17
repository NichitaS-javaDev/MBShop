package mb.shop.enums;

public enum CarType {
    SEDAN(1,"Sedan"),
    UNIVERSAL(2,"Universal"),
    CABRIO(3,"Cabrio"),
    AMG(4,"AMG"),
    BRABUS(5,"Brabus"),
    ;

    public int code;
    public String name;

    CarType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CarType getByType(String model){
        for (CarType carType : CarType.values()){
            if (carType.name.equalsIgnoreCase(model)){
                return carType;
            }
        }
        return null;
    }
}
