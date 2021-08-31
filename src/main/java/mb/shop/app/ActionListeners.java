package mb.shop.app;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Calendar;

public class ActionListeners {

    String stringHandler(Object o) {
        return o.toString().toLowerCase().replaceAll("[-_ ]","");
    }

    ActionListener addPurchaseListener(
            JTextField f_name, JTextField l_name, JComboBox<String> model, JComboBox<String> type, JLabel price){
        return e -> {
            FileWriter csv_writer = null;
            try {
                csv_writer = new FileWriter("src/main/resources/purchase-history.csv",true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (Car car : AFileReader.car_map.values()){
                if (car.model.toString().equals(
                        new Car().getCarByModel(model.getSelectedItem().toString()).toString()
                ) && car.type.toString().equals(
                       new Car().getCarByType(type.getSelectedItem().toString()).toString()
                )){
                    try {
                        csv_writer.append(String.join(
                                ", ", f_name.getText(), l_name.getText(), String.valueOf(car.model.code),
                                String.valueOf(car.type.code), price.getText(),
                                Calendar.getInstance().getTime().toString())
                        );
                        csv_writer.append("\n");
                        csv_writer.flush();
                        csv_writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
    }

    ActionListener addHistoryListener(){
        return e -> {
            new HistoryWindow().createHistoryWindow();
        };
    }

    ItemListener addPreviewAndPriceListener(JLabel price, JComboBox<String> model, JComboBox<String> type, JLabel preview){
        return e -> {
            price.setText("");
            for (Car car : AFileReader.car_map.values()) {
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

}
