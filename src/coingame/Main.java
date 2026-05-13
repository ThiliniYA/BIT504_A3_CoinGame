package coingame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    public Main() {
        // Window settings
        setTitle("Coin Game");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel
        add(new Panel());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}