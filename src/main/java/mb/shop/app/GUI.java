package mb.shop.app;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

    protected void createGUI(){
        Car car = new Car();
        BufferedImage logo = null;
        JLabel text, current_price;
        Dimension combo_size = new Dimension(250,20);
        Dimension button_size = new Dimension(250,40);
        Font label_font = new Font("",Font.BOLD,20);
        Font button_font = new Font("",Font.BOLD,20);
        Color button_bg = new Color(204,204,204);
        FlowLayout main_panel_loyout = new FlowLayout(FlowLayout.CENTER);
        main_panel_loyout.setHgap(50);
        main_panel_loyout.setVgap(60);

        JFrame frame = new JFrame("Mercedes-Benz");
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setBackground(new Color(255,255,255));

        frame.setSize(1400,900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel logo_panel = new JPanel();
        logo_panel.setPreferredSize(new Dimension(1350,320));
        logo_panel.setBorder(new WindowsBorders.DashedBorder(Color.black));
        frame.add(logo_panel);

        JPanel main_panel = new JPanel(main_panel_loyout);
        main_panel.setPreferredSize(new Dimension(950,550));
        main_panel.setBorder(new WindowsBorders.DashedBorder(Color.black));
        frame.add(main_panel);

        JPanel preview_panel = new JPanel();
        preview_panel.setPreferredSize(new Dimension(400,550));
        preview_panel.setBorder(new WindowsBorders.DashedBorder(Color.black));
        frame.add(preview_panel);

        try {
            logo = ImageIO.read(new File("src/main/resources/img/mb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic_label = new JLabel(new ImageIcon(logo));
        pic_label.setBorder(new WindowsBorders.DashedBorder(new Color(1)));
        pic_label.setPreferredSize(new Dimension(1350,350));
        logo_panel.add(pic_label);

        text = new JLabel("Car Model : ");
        text.setFont(label_font);
        main_panel.add(text);

        JComboBox<String> models = new JComboBox<>(car.getModelsName());
        models.setPreferredSize(combo_size);
        main_panel.add(models);

        text = new JLabel("Car Type : ");
        text.setFont(label_font);
        main_panel.add(text);

        JComboBox<String> types = new JComboBox<>(car.getCarTypes());
        types.setPreferredSize(combo_size);
        main_panel.add(types);

        text = new JLabel("Price :");
        text.setPreferredSize(new Dimension(230,100));
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setFont(label_font);
        main_panel.add(text);

        current_price = new JLabel("00.000x");
        current_price.setPreferredSize(new Dimension(470,100));
        current_price.setHorizontalAlignment(SwingConstants.LEFT);
        current_price.setFont(label_font);
        main_panel.add(current_price);

        text = new JLabel("First Name :");
        text.setFont(label_font);
        main_panel.add(text);

        JTextField first_name = new JTextField();
        first_name.setPreferredSize(combo_size);
        main_panel.add(first_name);

        text = new JLabel("Last Name :");
        text.setFont(label_font);
        main_panel.add(text);

        JTextField last_name = new JTextField();
        last_name.setPreferredSize(combo_size);
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
        preview.setPreferredSize(new Dimension(500,500));
        preview.setBorder(new WindowsBorders.DashedBorder(new Color(1)));
        //frame.add(preview);


        frame.setVisible(true);

    }

}
