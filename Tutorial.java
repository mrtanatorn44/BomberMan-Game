package BomberMan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Tutorial extends JPanel {
    private static Font font1 = new Font("MonoSpaced",Font.PLAIN,30);
    private static Border border = BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE);
    private static Image bg;
    public Tutorial(JFrame main, CardLayout cards) {
        try {
            bg = ImageIO.read(new File("src/BomberMan/images/bg_tutor.png"));
        } catch (IOException e) {
            System.out.println("cant find some picture in Menu class");
        }

        setBackground(new Color(30,30,30));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(520));

        JButton back_butt = new JButton("Back to menu");
        back_butt.setAlignmentX(Component.CENTER_ALIGNMENT);
        back_butt.setMaximumSize(new Dimension(400,50));
        back_butt.setFont(font1);
        back_butt.setBorder(border);
        back_butt.setBackground(Color.DARK_GRAY);
        back_butt.setForeground(Color.WHITE);
        back_butt.setBorder(border);
        add(back_butt, Component.CENTER_ALIGNMENT);

        back_butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Sound.play("src/BomberMan/sounds/button.wav");
                cards.show(main.getContentPane(), "menu");
                setFocusable(false);
            }
        });
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);
    }
}
