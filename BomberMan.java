package BomberMan;
import javax.swing.*;
import java.awt.*;

public class BomberMan {
    public static JFrame frame;
    public static CardLayout cards;
    public static Menu menu_panel;
    public static Game game_panel;
    public static Tutorial tutor_panel;
    public static void main(String[] args) {
        cards = new CardLayout();
        frame = new JFrame("Bomber Man with friend");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(610, 630);
        frame.setLocation(50,50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(cards);

        game_panel = new Game(frame, cards);
        tutor_panel = new Tutorial(frame, cards);
        menu_panel = new Menu(frame, cards, game_panel);

        frame.add(menu_panel, "menu");
        frame.add(tutor_panel, "tutor");
        frame.add(game_panel, "game");
    }
}
