package coingame;

import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {

    // Position
    private int xPosition;
    private int yPosition;

    // Velocity
    private int xVelocity;
    private int yVelocity;

    // Size
    private int width;
    private int height;

    // Initial position (used for resetting)
    private int initialXPosition;
    private int initialYPosition;

    // Color
    private Color color;

    // Rectangle for collision detection
    public Rectangle getRectangle() {
        return new Rectangle(xPosition, yPosition, width, height);
    }

    // Set initial position
    public void setInitialPosition(int x, int y) {
        this.initialXPosition = x;
        this.initialYPosition = y;
    }

    // Reset to initial position
    public void resetToInitialPosition() {
        this.xPosition = initialXPosition;
        this.yPosition = initialYPosition;
    }

    // Boundary safe setters
    public void setXPosition(int x, int screenWidth) {
        if (x < 0) {
            this.xPosition = 0;
        } else if (x + width > screenWidth) {
            this.xPosition = screenWidth - width;
        } else {
            this.xPosition = x;
        }
    }

    public void setYPosition(int y, int screenHeight) {
        if (y < 0) {
            this.yPosition = 0;
        } else if (y + height > screenHeight) {
            this.yPosition = screenHeight - height;
        } else {
            this.yPosition = y;
        }
    }

    // Getters and setters
    public int getXPosition() { return xPosition; }
    public void setXPosition(int x) { this.xPosition = x; }

    public int getYPosition() { return yPosition; }
    public void setYPosition(int y) { this.yPosition = y; }

    public int getXVelocity() { return xVelocity; }
    public void setXVelocity(int xVelocity) { this.xVelocity = xVelocity; }

    public int getYVelocity() { return yVelocity; }
    public void setYVelocity(int yVelocity) { this.yVelocity = yVelocity; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getInitialXPosition() { return initialXPosition; }
    public int getInitialYPosition() { return initialYPosition; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}
