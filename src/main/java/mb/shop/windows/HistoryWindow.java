package mb.shop.windows;

import javax.swing.*;
import java.awt.*;

public class HistoryWindow {
    JFrame frame = new JFrame("MB Purchase History");
    public GridLayout gridLayout = new GridLayout();
    public JPanel panel = new JPanel(gridLayout);
    ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_NEVER);

    public void createHistoryWindow() {
        frame.setIconImage(new ImageIcon("src/main/resources/img/mb_ico.png").getImage());

        scrollPane.add(panel);

        frame.setSize(1350,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.add(scrollPane);

        frame.setVisible(true);
    }

}
