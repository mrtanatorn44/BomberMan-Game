package BomberMan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Menu extends JPanel {
    private static Font font1 = new Font("MonoSpaced",Font.PLAIN,30);
    // border for button design
    private static Border border = BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE);
    private static Image bg;
    public Menu(JFrame main, CardLayout cards, JPanel game) {
        setBackground(new Color(30,30,30));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        try {
            bg = ImageIO.read(new File("src/BomberMan/images/bg_main.png"));
        } catch (IOException e) {
            System.out.println("cant find some picture in Menu class");
        }

        // create space on top
        add(Box.createVerticalStrut(100));

        // https://cooltext.com/Logo-Design-Neon
        // read game logo
        ImageIcon menu_icon = new ImageIcon("src/BomberMan/images/menu.png");
        // create logo as Jlabel
        JLabel menu_lb = new JLabel(menu_icon);
        menu_lb.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(menu_lb, Component.CENTER_ALIGNMENT);

        // create spawn below logo
        add(Box.createVerticalStrut(70));

        // start game button
        JButton start_butt = new JButton("Lets play");
        start_butt.setAlignmentX(Component.CENTER_ALIGNMENT);
        start_butt.setMaximumSize(new Dimension(400,50));
        start_butt.setFont(font1);
        start_butt.setBackground(Color.DARK_GRAY);
        start_butt.setForeground(Color.WHITE);
        start_butt.setBorder(border);
        add(start_butt, Component.CENTER_ALIGNMENT);

        // a little bit space
        add(Box.createVerticalStrut(10));

        // tutorial button
        JButton tutor_butt = new JButton("How to play");
        tutor_butt.setAlignmentX(Component.CENTER_ALIGNMENT);
        tutor_butt.setMaximumSize(new Dimension(400,50));
        tutor_butt.setFont(font1);
        tutor_butt.setBackground(Color.DARK_GRAY);
        tutor_butt.setForeground(Color.WHITE);
        tutor_butt.setBorder(border);
        add(tutor_butt, Component.CENTER_ALIGNMENT);

        // a little bit space
        add(Box.createVerticalStrut(10));

        // exit button
        JButton close_butt = new JButton("Exit game");
        close_butt.setAlignmentX(Component.CENTER_ALIGNMENT);
        close_butt.setMaximumSize(new Dimension(400,50));
        close_butt.setFont(font1);
        close_butt.setBackground(Color.DARK_GRAY);
        close_butt.setForeground(Color.WHITE);
        close_butt.setBorder(border);
        add(close_butt, Component.CENTER_ALIGNMENT);

        // add action listener to start button
        start_butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // button active sound
                Sound.play("src/BomberMan/sounds/button.wav");
                // card layout change to "game"
                cards.show(main.getContentPane(), "game");
                /*
                 * set focusable menu to "false"
                 * and set focusable, request focus to game
                 * because game need to use actionlistener and keylistener
                 */
                setFocusable(false);
                game.setFocusable(true);
                game.requestFocus();
            }
        });
        // add action listener to tutorial button
        tutor_butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Sound.play("src/BomberMan/sounds/button.wav");
                cards.show(main.getContentPane(), "tutor");
            }
        });
        // add action listener to exit button
        close_butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Sound.play("src/BomberMan/sounds/button.wav");
                System.exit(0);
            }
        });
    }
    // draw bg
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);
    }
}
