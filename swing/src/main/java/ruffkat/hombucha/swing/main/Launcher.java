package ruffkat.hombucha.swing.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Launcher
        implements Runnable {

    @Override
    public void run() {
        configureSystemSettings();
        launchApplication();
    }

    private void configureSystemSettings() {
        try {
            System.setProperty("awt.useSystemAAFontSettings", "on");
            System.setProperty("swing.aatext", "true");
            System.setProperty("sun.java2d.opengl", "True");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void launchApplication() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:swing-context.xml");
        context.registerShutdownHook();
        HombuchaFrame frame = context.getBean(HombuchaFrame.class);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.run();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Launcher());
    }
}
