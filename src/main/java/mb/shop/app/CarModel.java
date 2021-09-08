package mb.shop.app;

public enum CarModel {
    A_class(1,"A-Class"),
    C_class(2,"C-Class"),
    E_class(3,"E-Class"),
    S_class(4,"S-Class"),
    S_class_coupe(5,"S-Class Coupe")
    ;

    public int code;
    public String name;

    CarModel(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
