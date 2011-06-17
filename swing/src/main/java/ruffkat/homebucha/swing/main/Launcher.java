package ruffkat.homebucha.swing.main;

import javax.swing.SwingUtilities;

public class Launcher implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Launcher());
    }
}
