package mb.shop.app;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import javafx.scene.layout.Background;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

    protected void createGUI(){
        ActionListeners actionListeners = new ActionListeners();
        Car car = new Car();
        //BufferedImage logo = null;
        JLabel text, current_price;
        //current_price = new JLabel(); //// delete it
        Dimension combo_size = new Dimension(250,35);
        Dimension button_size = new Dimension(250,40);
        //Dimension small_logo_size = new Dimension(310,150);
        Font combo_font = new Font("",Font.BOLD,18);
        Font label_font = new Font("",Font.BOLD,20);
        Font button_font = new Font("",Font.BOLD,20);
        Color button_bg = new Color(204,204,204);
        Color text_field_bg = new Color(238,238,238);
        FlowLayout main_panel_loyout = new FlowLayout(FlowLayout.CENTER);
        main_panel_loyout.setHgap(50);
        main_panel_loyout.setVgap(60);

        JFrame frame = new JFrame("Mercedes-Benz");
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        //frame.setBackground(new Color(255,255,255));
        frame.setIconImage(new ImageIcon("src/main/resources/img/mb_ico.png").getImage());

        frame.setSize(1630,875);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel logo_panel = new JPanel();
        logo_panel.setPreferredSize(new Dimension(1675,265));
        //logo_panel.setBorder(new WindowsBorders.DashedBorder(Color.black));
        frame.add(logo_panel);

        JPanel main_panel = new JPanel(main_panel_loyout);
        main_panel.setPreferredSize(new Dimension(950,550));
        //main_panel.setBorder(new WindowsBorders.DashedBorder(Color.black));
        frame.add(main_panel);

        JPanel preview_panel = new JPanel();
        preview_panel.setPreferredSize(new Dimension(650,550));
        //preview_panel.setBorder(new WindowsBorders.DashedBorder(Color.black));
        frame.add(preview_panel);

//        try {
//            logo = ImageIO.read(new File());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        JLabel pic_label_sign = new JLabel(new ImageIcon("src/main/resources/img/mb_sign.png"));
        //pic_label_sign.setBorder(new WindowsBorders.DashedBorder(new Color(1)));
        pic_label_sign.setPreferredSize(new Dimension(260,265));
        logo_panel.add(pic_label_sign);

        JLabel pic_label_name = new JLabel(new ImageIcon("src/main/resources/img/mb_name.png"));
        //pic_label_name.setBorder(new WindowsBorders.DashedBorder(new Color(1)));
        pic_label_name.setPreferredSize(new Dimension(750,265));
        logo_panel.add(pic_label_name);

        text = new JLabel("Car Model : ");
        text.setFont(label_font);
        main_panel.add(text);

        JComboBox<String> models = new JComboBox<>(car.getModelsName());
        models.setPreferredSize(combo_size);
        models.setFont(combo_font);
        main_panel.add(models);

        text = new JLabel("Car Type : ");
        text.setFont(label_font);
        main_panel.add(text);

        JComboBox<String> types = new JComboBox<>(car.getCarTypes());
        types.setPreferredSize(combo_size);
        types.setFont(combo_font);
        main_panel.add(types);

        text = new JLabel("Price :");
        text.setPreferredSize(new Dimension(215,100));
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setFont(label_font);
        main_panel.add(text);

        current_price = new JLabel();
        current_price.setPreferredSize(new Dimension(495,100));
        current_price.setHorizontalAlignment(SwingConstants.LEFT);
        current_price.setFont(label_font);
        main_panel.add(current_price);

        text = new JLabel("First Name :");
        text.setFont(label_font);
        main_panel.add(text);

        JTextField first_name = new JTextField();
        first_name.setPreferredSize(combo_size);
        first_name.setFont(combo_font);
        first_name.setBackground(text_field_bg);
        main_panel.add(first_name);

        text = new JLabel("Last Name :");
        text.setFont(label_font);
        text.setPreferredSize(new Dimension(115,75));
        main_panel.add(text);

        JTextField last_name = new JTextField();
        last_name.setPreferredSize(combo_size);
        last_name.setFont(combo_font);
        last_name.setBackground(text_field_bg);
        main_panel.add(last_name);

        JButton purchase_button = new JButton("Purchase");
        purchase_button.setPreferredSize(button_size);
        purchase_button.setFont(button_font);
        purchase_button.setBackground(button_bg);
        main_panel.add(purchase_button);

        JButton history_button = new JButton("History");
        history_button.setPreferredSize(button_size);
        history_button.setFont(button_font);
        history_button.setBackground(button_bg);
        main_panel.add(history_button);

        JLabel preview = new JLabel();
        preview.setPreferredSize(new Dimension(650,390));
        //preview.setBorder(new WindowsBorders.DashedBorder(Color.black));
        preview.setHorizontalAlignment(SwingConstants.CENTER);
        preview.setVerticalAlignment(SwingConstants.CENTER);
        preview_panel.add(preview);

        JLabel amg_logo = new JLabel(new ImageIcon("src/main/resources/img/amg.png"));
        amg_logo.setPreferredSize(new Dimension(420,150));
        //amg_logo.setBorder(new WindowsBorders.DashedBorder(Color.black));
        amg_logo.setHorizontalAlignment(SwingConstants.CENTER);
        amg_logo.setVerticalAlignment(SwingConstants.CENTER);
        preview_panel.add(amg_logo);

        JLabel brabus_logo = new JLabel(new ImageIcon("src/main/resources/img/brabus.png"));
        brabus_logo.setPreferredSize(new Dimension(200,150));
        //brabus_logo.setBorder(new WindowsBorders.DashedBorder(Color.red));
        brabus_logo.setHorizontalAlignment(SwingConstants.CENTER);
        brabus_logo.setVerticalAlignment(SwingConstants.CENTER);
        preview_panel.add(brabus_logo);

        models.addItemListener(actionListeners.addPreviewAndPriceListener(
                current_price, models, types, preview
        ));

        types.addItemListener(actionListeners.addPreviewAndPriceListener(
                current_price, models, types, preview
        ));

        models.setSelectedItem("S-Class");
        types.setSelectedItem("Brabus");

        purchase_button.addActionListener(
                actionListeners.addPurchaseListener(first_name, last_name, models, types, current_price)
        );

        history_button.addActionListener(actionListeners.addHistoryListener());

        //frame.pack();
        frame.setVisible(true);
    }
}
