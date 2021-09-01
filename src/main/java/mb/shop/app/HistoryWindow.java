package mb.shop.app;

import javax.swing.*;
import java.awt.*;

public class HistoryWindow {
    JFrame frame = new JFrame("MB Purchase History");
    GridLayout gridLayout = new GridLayout(20,5);
    JPanel panel = new JPanel(gridLayout);
    /*JScrollPane scrollPane = new JScrollPane(
            //panel/*,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED*///)
            //;
    ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_NEVER);

    public void createHistoryWindow() {
        //frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setIconImage(new ImageIcon("src/main/resources/img/mb_ico.png").getImage());
        //gridLayout.setColumns(6);
        scrollPane.add(panel);

        frame.setSize(1350,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //panel.setPreferredSize(new Dimension(1265,540));
        //panel.setMinimumSize(new Dimension(1265,540));
        //panel.setSize(1265,540);

        frame.add(scrollPane);

        frame.setVisible(true);
    }

}
