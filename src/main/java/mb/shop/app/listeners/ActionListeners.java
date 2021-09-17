package mb.shop.app.listeners;

import mb.shop.entities.Car;
import mb.shop.entities.Purchase;
import mb.shop.dataCaches.CarsCache;
import mb.shop.dataCaches.PurchaseHistoryCache;
import mb.shop.windows.HistoryWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.List;

public class ActionListeners {

    JLabel createJLabel(String label_text){
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(200,30));
        label.setFont(new Font("",Font.BOLD,16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setText(label_text);
        return label;
    }

    boolean ifInputDataIsCorrect(JLabel price, JTextField first_name, JTextField last_name){
        return first_name.getText().length() >= 3 &&
                last_name.getText().length() >= 3 &&
                !price.getText().equalsIgnoreCase("not available");
    }

    public ActionListener addPurchaseListener(
            JTextField f_name, JTextField l_name, JComboBox<String> model, JComboBox<String> type, JLabel price){
        return e -> {
            PurchaseHistoryCache purchaseHistoryCache = new PurchaseHistoryCache();
            purchaseHistoryCache.addPurchase(f_name.getText(), l_name.getText(), model.getSelectedItem().toString(),
                    type.getSelectedItem().toString(), price.getText(),
                    Calendar.getInstance().getTime().toString().substring(0,19));

            f_name.setText("");
            l_name.setText("");
            String s = price.getText();
            price.setText("");
            price.setText(s);

        };
    }

    public ActionListener addHistoryListener(){
        return e -> {
            HistoryWindow window = new HistoryWindow();
            List<Purchase> purchase_list = new PurchaseHistoryCache().getPurchases();

            window.gridLayout.setRows(purchase_list.size()+1);
            window.gridLayout.setColumns(6);

            window.panel.add(createJLabel("First Name"));
            window.panel.add(createJLabel("Last Name"));
            window.panel.add(createJLabel("Model"));
            window.panel.add(createJLabel("Type"));
            window.panel.add(createJLabel("Price"));
            window.panel.add(createJLabel("Date"));

            for (Purchase purchase : purchase_list){
                window.panel.add(createJLabel(purchase.first_name));
                window.panel.add(createJLabel(purchase.last_name));
                window.panel.add(createJLabel(purchase.model));
                window.panel.add(createJLabel(purchase.type));
                window.panel.add(createJLabel(purchase.price));
                window.panel.add(createJLabel(purchase.date));
            }

            window.createHistoryWindow();
        };
    }

    public ItemListener addPreviewAndPriceListener(JLabel price, JComboBox<String> model, JComboBox<String> type, JLabel preview){
        return e -> {
            price.setText("");
            CarsCache carsCache = new CarsCache();
            Car car = carsCache.getCar(
                    String.valueOf(new Car().getCarByModel(String.valueOf(model.getSelectedItem())).code),
                    String.valueOf(new Car().getCarByType(String.valueOf(type.getSelectedItem())).code)
            );

            if (car != null){
                price.setText(car.current_price + "$");
                preview.setIcon(new ImageIcon(car.img_path));
            }

            if (price.getText().equals("")){
                price.setText("Not Available");
                preview.setIcon(new ImageIcon("src/main/resources/img/not_available.png"));
            }
        };
    }

    public KeyListener addPurchaseButtonKeyListener(
            JLabel price, JTextField first_name, JTextField last_name, JButton purchase_button
    ){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                purchase_button.setEnabled(ifInputDataIsCorrect(price, first_name, last_name));
            }
        };
    }

    public PropertyChangeListener addPropertyChangeListener(
            JLabel price, JTextField first_name, JTextField last_name, JButton purchase_button
    ){
        return evt -> purchase_button.setEnabled(ifInputDataIsCorrect(price, first_name, last_name));
    }

}
