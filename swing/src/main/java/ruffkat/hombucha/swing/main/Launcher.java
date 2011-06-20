package ruffkat.hombucha.swing.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Launcher implements Runnable {

    @Override
    public void run() {
        try {
            System.setProperty("awt.useSystemAAFontSettings", "on");
            System.setProperty("swing.aatext", "true");
            System.setProperty("sun.java2d.opengl", "True");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HombuchaFrame frame = new HombuchaFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.run();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Launcher());
    }
}
