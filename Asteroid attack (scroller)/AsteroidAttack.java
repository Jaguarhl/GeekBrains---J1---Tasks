/*
 * Asteroid Attack scroller game (Java lvl 1 parctice)
 * @author Dmitry Kartsev, based on SpaceInviders of Sergey (biblelamp) - https://github.com/biblelamp
 * @version 0.1.1 17/09/2016
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
import java.lang.*;

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
    final int GAME_SPEED = 5; // speed of game
    public float timeoutMin = 1;
    public float timeoutMax = 1.5f;
    private float curTimeout;
    private static float tmpSpeed;
    private float timeout;
    public static boolean gameOver;
    Image asteroid, ship, missile, m_explosion; // sprites for asteroids, spaceship, missile, explosive
    Canvas canvasPanel = new Canvas();
    Random random = new Random();
    PlayerShip playership = new PlayerShip(); // players spaceship
    Space space = new Space();
    volatile ArrayList<Missile> missiles = new ArrayList<Missile>(); // missiles, launched by player
    volatile ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>(); // missiles, launched by player
    volatile ArrayList<MissileBoom> m_explosions = new ArrayList<MissileBoom>(); // missile explosions

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
            m_explosion = ImageIO.read(new File("img/m_explosion.png"));
        } catch(IOException e) { e.printStackTrace(); }

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT) || (e.getKeyCode() == UP)|| (e.getKeyCode() == DOWN))
                    playership.setDirection(e.getKeyCode());
                if (e.getKeyCode() == FIRE)
                    playership.shotMissile();
            }
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT) || (e.getKeyCode() == UP)|| (e.getKeyCode() == DOWN)) {}
                playership.setDirection(0);
            }
        });
        setVisible(true);

        asteroids.add(new Asteroid(random.nextInt(FIELD_WIDTH), -50, 0, 15)); // let's start
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
            i = 0;
            for (Asteroid asteroid : asteroids) {
                if(asteroid.isEnable()) asteroid.fly();
                i++;
            }
            for (MissileBoom missile_boom : m_explosions) {
                if(missile_boom.isEnable()) missile_boom.explode();
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
        // clear dead asteroids
        for(int i = 0; i < asteroids.size(); i++) { // for missiles
            if(!asteroids.get(i).isEnable()) {
                asteroids.remove(i);
                asteroids.add(new Asteroid(random.nextInt(FIELD_WIDTH), -50, rnd(-3.7, 3.7), (int)rnd(14,38)));
            }
        }
        // if explosion is already gone, than we do not need it
        for(int i = 0; i < m_explosions.size(); i++) { // for missiles
            if(!m_explosions.get(i).isEnable()) m_explosions.remove(i);
        }
    }

    // some utility for generate positive and negative double values
    double rnd(double min, double max) {
        return min + (random.nextDouble() * (max - min));
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
        final int SPEED = 16; // speed of missile
        final int DAMAGE = 35; // what damage it do to asteroid / enemy
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

                if(checkTarget(this.x, this.y)) {
                    this.exists = false; // our missile is exploded
                    m_explosions.add(new MissileBoom(x, y));
                    System.out.println("Gotta! BOOM!!!");
                }
            }
        }

        boolean checkTarget(int x, int y) {
            for (Asteroid asteroid : asteroids) {
                double d = Math.sqrt(Math.pow((double)asteroid.getX()-(double)x, 2) + Math.pow((double)asteroid.getY()-(double)y, 2));
                if((asteroid.isEnable()) && (d <= asteroid.getRadius())) {
                    asteroid.getDamage(DAMAGE);
                    return true;
                }
            }
            return false;
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

        final int SPRITE_CELL = 72; // size of sprite cell
        final int RADIUS = 55; // for interaction calc
        final int DY = 30;
        final int DAMAGE = 10; // what damage it do on crash
        final int ANIM_FRAMES = 18; // how many frames do we have for animation

        int speed = 7; // speed of asteroid flying
        int anim_speed = 12; // speed of animation
        int x, y, flyTime, animTime, animPhase; // current position of asteroid, what course it is flying and phase of animation
        double traectory; // coeff for X dislocation
        boolean exists; // is it is?
        int live; // current level of durability (how much damage it needed for destroying)

        Asteroid (int x, int y, double direction, int speed)
        {
            this.x = x; // starting position
            this.y = y;
            this.traectory = direction;
            this.flyTime = 0;
            this.animTime = 0;
            this.speed = speed;
            this.anim_speed = speed / 2;
            this.live = 20;
            this.exists = true;
        }

        void fly() {
            if (exists) {
                if(this.flyTime > this.speed) {// checking, if our missile not too hurry )
                    y += DY;
                    x += this.traectory;
                    this.exists = (y + DY) < FIELD_HEIGHT + RADIUS;
                    this.flyTime = 0;
                    System.out.println(exists);
                    System.out.println(x + "," + y);
                }
                else this.flyTime++;

                if(this.animTime >= this.anim_speed) {
                    if(this.animPhase < ANIM_FRAMES) this.animPhase++;
                    else this.animPhase = 0;
                    animTime = 0;
                }
                else this.animTime++;
            }
        }

        void disable() { exists = false; }

        boolean isEnable() { return this.exists; }

        int getX() { return x; }
        int getY() { return y; }
        void getDamage(int damage) {
            this.live -= damage;
            if(this.live <=0 ) this.exists = false; // if asteroid is destroed, let's kill it
        }
        int getRadius() { return RADIUS; }

        void paint(Graphics g) {
            g.drawImage(asteroid, x-(RADIUS/2), y-(RADIUS/2), x-(RADIUS/2)+SPRITE_CELL, y-(RADIUS/2)+SPRITE_CELL, this.animPhase*SPRITE_CELL, 0, this.animPhase*SPRITE_CELL+SPRITE_CELL, SPRITE_CELL, null);
        }
    }

    class MissileBoom { // explosion after missile hit

        final int SPRITE_CELL = 72; // size of sprite cell
        final int ANIM_SPEED = 1; // speed of animation
        final int ANIM_FRAMES = 71; // how many frames do we have for animation

        int x, y, animTime, animPhase; // position of explosion and phase of animation
        boolean exists; // is it is?

        MissileBoom (int x, int y)
        {
            this.x = x; // starting position
            this.y = y;
            this.animTime = 0;
            this.exists = true;
        }

        void explode() {
            if (exists) {
                if(this.animTime >= ANIM_SPEED) {
                    if(this.animPhase < ANIM_FRAMES) this.animPhase++;
                    else this.exists = false; // end of explosion
                    animTime = 0;
                }
                else this.animTime++;
            }
        }

        void disable() { exists = false; }

        boolean isEnable() { return this.exists; }

        int getX() { return x; }
        int getY() { return y; }

        void paint(Graphics g) {
            g.drawImage(m_explosion, x-(SPRITE_CELL/2), y-(SPRITE_CELL/2), x-(SPRITE_CELL/2)+SPRITE_CELL, y-(SPRITE_CELL/2)+SPRITE_CELL, this.animPhase*SPRITE_CELL, 0, this.animPhase*SPRITE_CELL+SPRITE_CELL, SPRITE_CELL, null);
        }
    }

    class Space { // wave of asteroid attack

        volatile ArrayList<Asteroid> space = new ArrayList<Asteroid>();

        void generateSpace() {
            space.add(new Asteroid(random.nextInt(FIELD_WIDTH), -50, 0, 7));
        }

        void paint(Graphics g) {
            for (Asteroid asteroid : space) asteroid.paint(g);
        }
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
                for (Asteroid asteroid : asteroids) {
                    if (asteroid.isEnable()) asteroid.paint(g);
                }
                for (MissileBoom missile_boom : m_explosions) {
                    if (missile_boom.isEnable()) missile_boom.paint(g);
                }
                /*wave.paint(g);
                flash.paint(g);
                rays.paint(g);*/
            }
        }
    }
}