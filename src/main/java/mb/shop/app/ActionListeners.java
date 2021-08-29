package mb.shop.app;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class ActionListeners {
    String stringHandler(Object o){
        return o.toString().toLowerCase().replaceAll("[-_ ]","");
    }

//    ActionListener addPurchaseListener(JComboBox<String> model, JComboBox<String> type, JLabel price){
//        return e -> {
//            price.setText("");
//            for (Car car : AFileReader.car_map.values()) {
//                if (stringHandler((model.getSelectedItem())).equals(stringHandler(car.model))
//                        & stringHandler(type.getSelectedItem()).equals(stringHandler(car.type))){
//                    price.setText(car.current_price + "$");
//                    break;
//                }
//
//            }
//            if (price.getText().equals("")){
//                price.setText("Not Available");
//            }
//        };
//    }

    ItemListener addPreviewAndPriceListener(JLabel price, JComboBox model, JComboBox type, JLabel preview){
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
            }
        };
    }

}
