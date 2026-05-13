package coingame;

import java.awt.Color;
import java.util.Random;

public class Enemy extends Sprite {

    public static final int ENEMY_SIZE = 25;
    private static final Random rand = new Random();

    public Enemy(int startX, int startY) {
        setWidth(ENEMY_SIZE);
        setHeight(ENEMY_SIZE);
        setColor(Color.RED);

        setXPosition(startX);
        setYPosition(startY);

        setInitialPosition(startX, startY);

        // Random velocity: -3 to +3 but not zero
        int vx = rand.nextInt(7) - 3;
        int vy = rand.nextInt(7) - 3;

        if (vx == 0) vx = 1;
        if (vy == 0) vy = 1;

        setXVelocity(vx);
        setYVelocity(vy);
    }
}

