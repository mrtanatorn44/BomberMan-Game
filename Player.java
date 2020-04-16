package BomberMan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player {
    int x;
    int y;
    boolean status;
    String name;
    int block = 60;
    Font font;
    int[][] map;
    Image face, dead;
    String credit;

    public Player(String name, int x, int y, int[][] map) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.map = map;
        this.status = true;
        this.font = new Font("Sans Serif",Font.BOLD,20);
        this.credit = "tanatorn 6230300419";
    }
    public void left() {
        if (status) {
            this.x -= 1;
            if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3) {
                x += 1;
            }
        }
    }
    public void right() {
        if (status) {
            this.x += 1;
            if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3) {
                x -= 1;
            }
        }
    }
    public void up() {
         if (status) {
             this.y -= 1;
             if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3) {
                 y += 1;
             }
         }
    }
    public void down() {
        if (status) {
            this.y += 1;
            if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3) {
                y -= 1;
            }
        }
    }
    void updateForNewFrame() {
        // if player touch lava
        if (map[y][x] == 4) {
            status = false;
        }
    }
    void draw(Graphics g) {
        try {
            face = ImageIO.read(new File("src/BomberMan/images/face.png"));
            dead = ImageIO.read(new File("src/BomberMan/images/dead.png"));
        } catch (IOException e) {
            System.out.println("cant find some picture in BomberPlayer class");
        }

        // draw pic of character
        if (status) {
            g.drawImage(face,this.x * block,this.y * block,null);
        } else {
            g.drawImage(dead,this.x * block,this.y * block,null);
        }
        // draw name
        g.setFont(font);
        g.drawString(this.name, this.x * block + 50, this.y * block);
        g.setColor(Color.BLACK);
        g.drawString(this.name, this.x * block + 50 +2, this.y * block +2);
        g.setColor(Color.WHITE);
        g.drawString(this.name, this.x * block + 50, this.y * block);
    }
}
