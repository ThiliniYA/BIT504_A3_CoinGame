package coingame;

import java.awt.Color;

public class Player extends Sprite {

    public static final int PLAYER_SIZE = 25;

    public Player(int startX, int startY) {
        setWidth(PLAYER_SIZE);
        setHeight(PLAYER_SIZE);
        setColor(Color.GREEN);

        setXPosition(startX);
        setYPosition(startY);

        setInitialPosition(startX, startY);
    }
}
