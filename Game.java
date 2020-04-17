package BomberMan;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements ActionListener, KeyListener {
    /* 0 FLOOR as dirt
     * 1 WALL no Destroyable as stone
     * 2 WALL can Destroyable as wood
     * 3 BOMB as TNT
     * 4 EXPLODE as lava
     */
    public String p1_move_temp, p2_move_temp;
    public Player p1, p2;
    public Bomb bomb1, bomb2;
    public int[][] map;
    public Timer timer;
    public int block;
    public Image floor, wall, wall2, bomb, explode, face;
    public Border border;
    private Font font;
    private Font font2;
    private Font font3;

    public JFrame main;
    public CardLayout cards;

    public Game(JFrame main, CardLayout cards) {
        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(600, 600));
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        this.block = 60;
        this.bomb1 = null;
        this.bomb2 = null;
        this.main = main;
        this.cards = cards;
        this.p1_move_temp = "up";
        this.p2_move_temp = "down";
        this.map = new int[][] {{1,1,1,1,1,1,1,1,1,1},
                {1,0,0,2,2,2,2,2,2,1},
                {1,0,1,1,1,2,1,1,2,1},
                {1,2,1,2,2,2,2,1,2,1},
                {1,2,2,2,1,1,2,1,2,1},
                {1,2,1,2,1,1,2,2,2,1},
                {1,2,1,2,2,2,2,1,2,1},
                {1,2,1,1,2,1,1,1,0,1},
                {1,2,2,2,2,2,2,0,0,1},
                {1,1,1,1,1,1,1,1,1,1}};

        this.border = BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE);
        this.font = new Font("Sans Serif",Font.PLAIN,20);
        this.font2 = new Font("Sans Serif",Font.BOLD,17);
        this.font3 = new Font("Sans Serif",Font.BOLD,50);

        this.timer = new Timer(30, this);
        timer.start();

        try {
            floor = ImageIO.read(new File("src/BomberMan/images/floor.png"));
            wall = ImageIO.read(new File("src/BomberMan/images/wall.png"));
            wall2 = ImageIO.read(new File("src/BomberMan/images/wall2.png"));
            bomb = ImageIO.read(new File("src/BomberMan/images/bomb.png"));
            explode = ImageIO.read(new File("src/BomberMan/images/explode.png"));
            face = ImageIO.read(new File("src/BomberMan/images/face.png"));
        } catch (IOException e) {
            System.out.println("cant find some picture in Game class");
        }
    }

    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();
        if (code == KeyEvent.VK_A) {
            p1.left();
            p1_move_temp = "left";
        } else if (code == KeyEvent.VK_D) {
            p1.right();
            p1_move_temp = "right";
        } else if (code == KeyEvent.VK_W) {
            p1.up();
            p1_move_temp = "up";
        } else if (code == KeyEvent.VK_S) {
            p1.down();
            p1_move_temp = "down";
        } else if (code == KeyEvent.VK_SPACE && bomb1 == null && p1.status) {
            Sound.play("src/BomberMan/sounds/dirt.wav");
            // create bomb
            bomb1 = new Bomb(p1.x, p1.y);
            // update map
            map = bomb1.addCol(map);

            // player get out bomb position
            switch (p1_move_temp) {
                case "left" : p1.right();
                    break;
                case "right" : p1.left();
                    break;
                case "up" : p1.down();
                    break;
                case "down" : p1.up();
                    break;
            }

            // bomb effect block (destroy wall)
            Timer timer_bomb = new Timer(1000, e -> {
                Sound.play("src/BomberMan/sounds/bomb.wav");
                map = bomb1.explodeRadius(map);
            });
            timer_bomb.setRepeats(false);
            timer_bomb.start();

            // bomb make lava after explode
            // then delete bomb by set to null
            Timer timer_explode = new Timer(2000, i -> {
                map = bomb1.explodeAction(map);
                bomb1 = null ;
            });
            timer_explode.setRepeats(false);
            timer_explode.start();
        }

        if (code == KeyEvent.VK_LEFT) {
            p2.left();
            p2_move_temp = "left";
        } else if (code == KeyEvent.VK_RIGHT) {
            p2.right();
            p2_move_temp = "right";
        } else if (code == KeyEvent.VK_UP) {
            p2.up();
            p2_move_temp = "up";
        } else if (code == KeyEvent.VK_DOWN) {
            p2.down();
            p2_move_temp = "down";
        } else if (code == KeyEvent.VK_NUMPAD0 && bomb2 == null && p2.status) {
            Sound.play("src/BomberMan/sounds/dirt.wav");
            // create bomb
            bomb2 = new Bomb(p2.x, p2.y);
            // update map
            map = bomb2.addCol(map);

            // player get out bomb position
            switch (p2_move_temp) {
                case "left" : p2.right();
                    break;
                case "right" : p2.left();
                    break;
                case "up" : p2.down();
                    break;
                case "down" : p2.up();
                    break;
            }

            // bomb effect block (destroy wall)
            Timer timer_bomb = new Timer(1000, e -> {
                Sound.play("src/BomberMan/sounds/bomb.wav");
                map = bomb2.explodeRadius(map);
            });
            timer_bomb.setRepeats(false);
            timer_bomb.start();

            // bomb make lava after explode
            // then delete bomb by set to null
            Timer timer_explode = new Timer(2000, i -> {
                map = bomb2.explodeAction(map);
                bomb2 = null ;
            });
            timer_explode.setRepeats(false);
            timer_explode.start();
        }

        // action for exit game layout
        if (code == KeyEvent.VK_ESCAPE && bomb1 == null && bomb2 == null) {
            resetGame();
            setFocusable(false);

            cards.show(main.getContentPane(),"menu");
        }
    }
    public void resetGame(){
        // set map, player, bomb to default
        this.map = new int[][] {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 2, 2, 2, 2, 2, 2, 1},
                {1, 0, 1, 1, 1, 2, 1, 1, 2, 1},
                {1, 2, 1, 2, 2, 2, 2, 1, 2, 1},
                {1, 2, 2, 2, 1, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 1, 2, 2, 2, 1},
                {1, 2, 1, 2, 2, 2, 2, 1, 2, 1},
                {1, 2, 1, 1, 2, 1, 1, 1, 0, 1},
                {1, 2, 2, 2, 2, 2, 2, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        p1 = null;
        p2 = null;
        bomb1 = null;
        bomb2 = null;
        p1_move_temp = "up";
        p2_move_temp = "down";
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw map
        for (int row = 0; row < 10 ; row++) {
            for (int col = 0; col < 10 ; col++) {
                if (map[row][col] == 0) {
                    g.drawImage(floor,col*block,row*block,this);
                } else if (map[row][col] == 1) {
                    g.drawImage(wall,col*block,row*block,this);
                } else if (map[row][col] == 2) {
                    g.drawImage(wall2,col*block,row*block,this);
                } else if (map[row][col] == 3) {
                    g.drawImage(bomb,col*block,row*block,this);
                } else if (map[row][col] == 4) {
                    g.drawImage(explode,col*block,row*block,this);
                }
            }
        }
        if (p1 == null) {
            p1 = new Player("P1", 1, 1, map);
            p2 = new Player("P2", 8, 8, map);
        }
        p2.draw(g);
        p1.draw(g);

        // draw helper menu
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Back to menu by press ESCAPE key",130+2,560+2);
        g.setColor(Color.WHITE);
        g.drawString("Back to menu by press ESCAPE key",130,560);

        // show safe to leave for exit game without exception
        if (bomb1 == null && bomb2 == null) {
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("(SAFE TO LEAVE)",220+2,580+2);
            g.setColor(Color.GREEN);
            g.drawString("(SAFE TO LEAVE)",220,580);
        }

        // player died condition
        if (!p1.status) {
            Sound.play("src/BomberMan/sounds/damage.wav");
            g.setFont(font3);
            g.setColor(Color.BLACK);
            g.drawString("P2 is winner!",150+5,300+5);
            g.setColor(Color.YELLOW);
            g.drawString("P2 is winner!",150,300);
            p2.status = false;
            timer.stop();
        } else if (!p2.status) {
            Sound.play("src/BomberMan/sounds/damage.wav");
            g.setFont(font3);
            g.setColor(Color.BLACK);
            g.drawString("P1 is winner!",150+5,300+5);
            g.setColor(Color.YELLOW);
            g.drawString("P1 is winner!",150,300);
            p1.status = false;
            timer.stop();
        }
    }
    public void actionPerformed(ActionEvent event) {
        if (p1 != null) {
            p1.updateForNewFrame();
            p2.updateForNewFrame();
        }
        repaint();
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
