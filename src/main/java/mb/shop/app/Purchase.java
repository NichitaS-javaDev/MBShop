package mb.shop.app;

public class Purchase {
    public String first_name, last_name, type, model, price, date;

    public Purchase(String first_name, String last_name, String model, String type, String price, String date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
        this.model = model;
        this.date = date;
        this.price = price;
    }
}
