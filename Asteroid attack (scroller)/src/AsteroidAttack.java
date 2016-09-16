/*
 * Asteroid Attack scroller game (Java lvl 1 parctice)
 * @author Dmitry Kartsev, based on SpaceInviders of Sergey (biblelamp) - https://github.com/biblelamp
 * @version 0.0.2 16/09/2016
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.util.Random;
import javax.imageio.*;
import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class AsteroidAttack extends JFrame {

    final String NAME_OF_GAME = "Asteroid Attack! Scroller Game";
    final int POINT_SCALE = 2;
    final int FIELD_WIDTH = 400*POINT_SCALE;
    final int FIELD_HEIGHT = 300*POINT_SCALE;
    final int START_LOCATION = 150;
    final int FIELD_DX = 7; // determined experimentally
    final int FIELD_DY = 26;
    final int STEP_X = 5; // wave step left-right
    final int STEP_Y = 15; // wave step down
    final int GROUND_Y = FIELD_HEIGHT - 20;
    final int POINT_RADIUS = 80; // size of one point
    final int LEFT = 37; // key codes
    final int RIGHT = 39;
    final int DOWN = 40;
    final int UP = 38;
    final int FIRE = 32;
    final int GAME_SPEED = 20; // speed of game
    public float timeoutMin = 1;
    public float timeoutMax = 1.5f;
    private float curTimeout;
    private static float tmpSpeed;
    private float timeout;
    public static boolean gameOver;
    Image asteroid, ship, missile; // sprites for asteroids, spaceship, missile
    Canvas canvasPanel = new Canvas();
    Random random = new Random();
    PlayerShip playership = new PlayerShip(); // players spaceship
    ArrayList<Missile> missiles = new ArrayList<Missile>(); // missiles, launched by player

    public static void main(String args[]) {
        new AsteroidAttack().go();
    }

    AsteroidAttack() {
        setTitle(NAME_OF_GAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH + FIELD_DX, FIELD_HEIGHT + FIELD_DY);
        setResizable(false);
        canvasPanel.setBackground(Color.black);
        getContentPane().add(BorderLayout.CENTER, canvasPanel);

        // let's load sprites for ship & asteroid
        try {
            ship = ImageIO.read(new File("img/spaceship.png"));
            asteroid = ImageIO.read(new File("img/asteroid.png"));
            missile = ImageIO.read(new File("img/missile.png"));
        } catch(IOException e) { e.printStackTrace(); }

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)|| (e.getKeyCode() == UP)|| (e.getKeyCode() == DOWN))
                    playership.setDirection(e.getKeyCode());
                if (e.getKeyCode() == FIRE)
                    playership.shotMissile();
            }
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)) {}
                playership.setDirection(0);
            }
        });
        setVisible(true);
    }

    void go() { // main loop of game
        while (true) {
            try {
                Thread.sleep(GAME_SPEED);
            } catch (Exception e) { e.printStackTrace(); }
            canvasPanel.repaint();
            playership.move();
            int i = 0;
            for (Missile missile : missiles) {
                if(missile.isEnable()) missile.fly();
                i++;
            }
            clearObjects();
            /*flash.show();
            bang.show();
            ray.fly();
            rays.fly();
            wave.nextStep();
            if (wave.isDestroyed()) { // if the wave completely destroyed
                wave = new Wave();
                countLives++;
            }*/
        }
    }

    void clearObjects() { // let's delete empty objects from array lists
        for(int i = 0; i < missiles.size(); i++) { // for missiles
            if(!missiles.get(i).isEnable()) missiles.remove(i);
        }
    }

    class PlayerShip { // players ship
        final int WIDTH = 26;
        final int HEIGHT = 16;
        final int DX = 5;
        final int DY = 5;
        final long DELAY = 1500; // delay for next lunch
        int x, y, direction;
        long lastLunch; // here we will store last lunch time

        public PlayerShip() {
            x = 400;
            y = FIELD_HEIGHT - HEIGHT - 30;
            lastLunch = System.currentTimeMillis() - 1500; // this is needed to fire from fist seconds of game
        }

        void move() { // spaceship can move
            if (direction == LEFT && x > 40) x -= DX;
            if (direction == RIGHT && x < FIELD_WIDTH - WIDTH - 16) x += DX;
            if (direction == DOWN && y < FIELD_HEIGHT - HEIGHT - 16) y += DY;
            if (direction == UP && y > FIELD_HEIGHT/2 - HEIGHT - 16) y -= DY;
        }

        void setDirection(int direction) { this.direction = direction; }

        void shotMissile() {
            /*playSound(new File("sounds/shoot.wav"));
            ray.start(x, y);*/
            System.out.println(System.currentTimeMillis() - lastLunch);
            if((System.currentTimeMillis() - lastLunch) > DELAY) {
                missiles.add(new Missile(x, y));
                lastLunch = System.currentTimeMillis();
            }
        }

        int getX() { return x; }
        int getY() { return y; }
        int getWidth() { return WIDTH; }

        void paint(Graphics g) {
            g.drawImage(ship, x-(POINT_RADIUS/2), y-(POINT_RADIUS/2), null);
        }
    }

    class Missile { // players gun
        final int WIDTH = 2; // for accuracy calc
        final int HEIGHT = 30; // for accuracy calc
        final int DY = 30;
        final int SPEED = 4; // speed of missile
        int x, y, flyTime, lastLunch;
        boolean exists;

        Missile(int x, int y) {
            if (!exists) {
                this.exists = true;
                this.x = x + (playership.getWidth() - WIDTH) / 2;
                this.y = y - HEIGHT;
                this.flyTime = 0;
            }
        }

        void start(int x, int y) {
            if (!exists) {
                this.exists = true;
                this.x = x + (playership.getWidth() - WIDTH) / 2;
                this.y = y - HEIGHT;
                this.flyTime = 0;
            }
        }

        void fly() {
            if (exists) {
                if(flyTime > SPEED) // checking, if our missile not too hurry )
                {
                    y -= DY;
                    this.exists = (y + DY) > 0;
                    this.flyTime = 0;
                }
                else this.flyTime++;
            }
        }

        void disable() { exists = false; }

        boolean isEnable() { return exists; }

        int getX() { return x; }
        int getY() { return y; }
        int getLastLunch() { return lastLunch; }

        void paint(Graphics g) {
            g.drawImage(missile, x-7, y, null);
        }
    }

    class Asteroid { // asteroid, that attacks player

        void paint(Graphics g) {
            //g.drawImage(asteroid, x*POINT_RADIUS, y*POINT_RADIUS, null);
        }
    }

    class Wave { // wave of asteroid attack

    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (!gameOver) {
                playership.paint(g);
                for (Missile missile : missiles) {
                    if (missile.isEnable()) missile.paint(g);
                }
                /*wave.paint(g);
                flash.paint(g);
                rays.paint(g);*/
            }
        }
    }
}