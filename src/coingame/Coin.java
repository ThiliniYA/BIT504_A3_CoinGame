package coingame;

import java.awt.Color;

public class Coin extends Sprite {

    public static final int COIN_SIZE = 20;

    private boolean collected = false;

    public Coin(int startX, int startY) {
        setWidth(COIN_SIZE);
        setHeight(COIN_SIZE);
        setColor(Color.YELLOW);

        setXPosition(startX);
        setYPosition(startY);

        setInitialPosition(startX, startY);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}

