package coingame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel implements ActionListener, KeyListener {

    // -------------------------
    // GAME STATE ENUM
    // -------------------------
    public enum GameState {
        INITIALISING,
        PLAYING,
        GAME_WON,
        GAME_OVER
    }

    // -------------------------
    // PLAYER + MOVEMENT FLAGS
    // -------------------------
    private Player player;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private final int PLAYER_SPEED = 4;

    // -------------------------
    // HEALTH SYSTEM
    // -------------------------
    private int playerHealth = 3;
    private boolean invulnerable = false;
    private long invulnerableEndTime = 0;

    // -------------------------
    // ENEMIES
    // -------------------------
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private final int ENEMY_COUNT = 10;

    // -------------------------
    // COINS
    // -------------------------
    private ArrayList<Coin> coins = new ArrayList<>();
    private final int COIN_COUNT = 10;

    private Random rand = new Random();

    // -------------------------
    // VARIABLES
    // -------------------------
    private Timer timer;
    private GameState gameState = GameState.INITIALISING;

    // -------------------------
    // CONSTRUCTOR
    // -------------------------
    public Panel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(16, this);
        timer.start();
    }

    // -------------------------
    // RESET GAME
    // -------------------------
    private void resetGame() {

        // Reset health
        playerHealth = 3;
        invulnerable = false;

        // Create player in the center
        int startX = (800 / 2) - (Player.PLAYER_SIZE / 2);
        int startY = (600 / 2) - (Player.PLAYER_SIZE / 2);
        player = new Player(startX, startY);

        // Clear old enemies 
        enemies.clear();

        // Create new enemies
        for (int i = 0; i < ENEMY_COUNT; i++) {
            int x = rand.nextInt(800 - Enemy.ENEMY_SIZE);
            int y = rand.nextInt(600 - Enemy.ENEMY_SIZE);

            if (Math.abs(x - startX) < 100) x += 150;
            if (Math.abs(y - startY) < 100) y += 150;

            enemies.add(new Enemy(x, y));
        }

        // Clear old coins
        coins.clear();

        // Create new coins
        for (int i = 0; i < COIN_COUNT; i++) {
            int x = rand.nextInt(800 - Coin.COIN_SIZE);
            int y = rand.nextInt(600 - Coin.COIN_SIZE);

            coins.add(new Coin(x, y));
        }
    }

    // -------------------------
    // UPDATE METHOD
    // -------------------------
    public void update() {
        if (gameState != GameState.PLAYING) {
            return;
        }

        // -------------------------
        // PLAYER MOVEMENT
        // -------------------------
        int newX = player.getXPosition();
        int newY = player.getYPosition();

        if (upPressed) newY -= PLAYER_SPEED;
        if (downPressed) newY += PLAYER_SPEED;
        if (leftPressed) newX -= PLAYER_SPEED;
        if (rightPressed) newX += PLAYER_SPEED;

        player.setXPosition(newX, 800);
        player.setYPosition(newY, 600);

        // -------------------------
        // ENEMY MOVEMENT
        // -------------------------
        for (Enemy enemy : enemies) {

            int ex = enemy.getXPosition();
            int ey = enemy.getYPosition();
            int vx = enemy.getXVelocity();
            int vy = enemy.getYVelocity();

            if (ex + vx < 0 || ex + vx + enemy.getWidth() > 800) {
                enemy.setXVelocity(vx * -1);
            }

            if (ey + vy < 0 || ey + vy + enemy.getHeight() > 600) {
                enemy.setYVelocity(vy * -1);
            }

            enemy.setXPosition(enemy.getXPosition() + enemy.getXVelocity(), 800);
            enemy.setYPosition(enemy.getYPosition() + enemy.getYVelocity(), 600);
        }

        // -------------------------
        // ENEMY COLLISION - DAMAGE
        // -------------------------
        if (!invulnerable) {
            for (Enemy enemy : enemies) {
                if (player.getRectangle().intersects(enemy.getRectangle())) {

                    playerHealth--;

                    invulnerable = true;
                    invulnerableEndTime = System.currentTimeMillis() + 3000;

                    if (playerHealth <= 0) {
                        gameState = GameState.GAME_OVER;
                    }

                    break;
                }
            }
        } else {
            if (System.currentTimeMillis() > invulnerableEndTime) {
                invulnerable = false;
            }
        }

        // -------------------------
        // COIN COLLECTION
        // -------------------------
        for (Coin coin : coins) {
            if (!coin.isCollected() && player.getRectangle().intersects(coin.getRectangle())) {
                coin.setCollected(true);
            }
        }

        // Check win condition
        boolean allCollected = true;
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                allCollected = false;
                break;
            }
        }

        if (allCollected) {
            gameState = GameState.GAME_WON;
        }
    }

    // -------------------------
    // HEART STRING
    // -------------------------
    private String getHearts() {
        String hearts = "";
        for (int i = 0; i < playerHealth; i++) {
            hearts += "\u2665";  
        }
        return hearts;
    }

    // -------------------------
    // COIN COUNTER
    // -------------------------
    private int getCollectedCoins() {
        int count = 0;
        for (Coin coin : coins) {
            if (coin.isCollected()) {
                count++;
            }
        }
        return count;
    }

    // -------------------------
    // ACTION PERFORMED
    // -------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    // -------------------------
    // DRAWING
    // -------------------------
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new java.awt.Font("Segoe UI Symbol", java.awt.Font.PLAIN, 24));

        switch (gameState) {
            case INITIALISING:
                g.drawString("Press SPACE to start", 280, 300);
                break;

            case GAME_WON:
                g.drawString("Congratulations! You collected all coins!", 180, 300);
                g.drawString("Press SPACE to restart", 260, 340);
                break;

            case GAME_OVER:
                g.drawString("Game Over!", 330, 300);
                g.drawString("Press SPACE to restart", 260, 340);
                break;

            case PLAYING:

                // Draw Health text and coin count in white
                g.setColor(Color.WHITE);
                g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
                g.drawString("Health: ", 20, 30);
            
                g.setColor(Color.WHITE);
                g.drawString("Coins: " + getCollectedCoins() + " / " + COIN_COUNT, 20, 60);
                
                // Draw hearts in pink
                g.setColor(Color.PINK); 
                g.drawString(getHearts(), 110, 30);


                // Draw coins
                for (Coin coin : coins) {
                    if (!coin.isCollected()) {
                        g.setColor(coin.getColor());
                        g.fillOval(coin.getXPosition(), coin.getYPosition(),
                                   coin.getWidth(), coin.getHeight());
                    }
                }

                // Draw player (flash when invulnerable)
                if (invulnerable) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(player.getColor());
                }

                g.fillRoundRect(player.getXPosition(), player.getYPosition(),
                        player.getWidth(), player.getHeight(),
                        10, 10);

                // Draw enemies
                for (Enemy enemy : enemies) {
                    g.setColor(enemy.getColor());
                    g.fillRect(enemy.getXPosition(), enemy.getYPosition(),
                               enemy.getWidth(), enemy.getHeight());
                }

                break;
        }
    }

    // -------------------------
    // KEY LISTENER
    // -------------------------
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (gameState == GameState.INITIALISING) {
                resetGame();
                gameState = GameState.PLAYING;
            } else if (gameState == GameState.GAME_WON || gameState == GameState.GAME_OVER) {
                resetGame();
                gameState = GameState.INITIALISING;
            }
        }

        if (gameState == GameState.PLAYING) {
            if (key == KeyEvent.VK_UP) upPressed = true;
            if (key == KeyEvent.VK_DOWN) downPressed = true;
            if (key == KeyEvent.VK_LEFT) leftPressed = true;
            if (key == KeyEvent.VK_RIGHT) rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) upPressed = false;
        if (key == KeyEvent.VK_DOWN) downPressed = false;
        if (key == KeyEvent.VK_LEFT) leftPressed = false;
        if (key == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
