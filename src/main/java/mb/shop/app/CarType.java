package mb.shop.app;

public enum CarType {
    SEDAN(1,"Sedan"),
    UNIVERSAL(2,"Universal"),
    CABRIO(3,"Cabrio"),
    AMG(4,"AMG"),
    BRABUS(5,"Brabus"),
    ;

    int code;
    String name;

    CarType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
