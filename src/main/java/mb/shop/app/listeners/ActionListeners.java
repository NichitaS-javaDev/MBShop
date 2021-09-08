package mb.shop.app.listeners;

import mb.shop.app.Car;
import mb.shop.app.Purchase;
import mb.shop.dataCaches.CarsCache;
import mb.shop.dataCaches.PurchaseHistoryCache;
import mb.shop.windows.HistoryWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Calendar;
import java.util.List;

public class ActionListeners {

    String stringHandler(Object o) {
        return o.toString().toLowerCase().replaceAll("[-_ ]","");
    }

    JLabel createJLabel(String label_text){
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(200,30));
        label.setFont(new Font("",Font.BOLD,16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setText(label_text);
        return label;
    }

    boolean ifInputDataIsCorrect(JLabel price, JTextField first_name, JTextField last_name, JButton purchase_button){
        return first_name.getText().length() >= 3 &&
                last_name.getText().length() >= 3 &&
                !price.getText().equalsIgnoreCase("not available");
    }


    public ActionListener addPurchaseListener(
            JTextField f_name, JTextField l_name, JComboBox<String> model, JComboBox<String> type, JLabel price){
        return e -> {
            FileWriter csv_writer = null;
            try {
                csv_writer = new FileWriter("src/main/resources/purchase-history.csv",true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (Car car : CarsCache.carMap.values()){
                if (car.model.toString().equals(
                        new Car().getCarByModel(model.getSelectedItem().toString()).toString()
                ) && car.type.toString().equals(
                       new Car().getCarByType(type.getSelectedItem().toString()).toString()
                )){
                    try {
                        csv_writer.append(String.join(
                                ", ", f_name.getText(), l_name.getText(), String.valueOf(car.model.code),
                                String.valueOf(car.type.code), price.getText(),
                                Calendar.getInstance().getTime().toString().substring(0,19))
                        );
                        csv_writer.append("\n");
                        csv_writer.flush();
                        csv_writer.close();

                        f_name.setText("");
                        l_name.setText("");
                        String s = price.getText();
                        price.setText("");
                        price.setText(s);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
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
            for (Car car : CarsCache.carMap.values()) {
                if (stringHandler((model.getSelectedItem())).equals(stringHandler(car.model))
                        & stringHandler(type.getSelectedItem()).equals(stringHandler(car.type))){
                    price.setText(car.current_price + "$");
                    preview.setIcon(new ImageIcon(car.img_path));
                    break;
                }

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
                purchase_button.setEnabled(ifInputDataIsCorrect(price, first_name, last_name, purchase_button));
            }
        };
    }

    public PropertyChangeListener addPropertyChangeListener(
            JLabel price, JTextField first_name, JTextField last_name, JButton purchase_button
    ){
        return evt -> purchase_button.setEnabled(ifInputDataIsCorrect(price, first_name, last_name, purchase_button));
    }

}
