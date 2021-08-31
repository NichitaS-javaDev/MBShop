package mb.shop.app;

import javax.swing.*;
import java.awt.*;

public class HistoryWindow {

    public void createHistoryWindow() {
        JFrame frame = new JFrame("MB Purchase History");
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setIconImage(new ImageIcon("src/main/resources/img/mb_ico.png").getImage());

        frame.setSize(550,550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }

}
