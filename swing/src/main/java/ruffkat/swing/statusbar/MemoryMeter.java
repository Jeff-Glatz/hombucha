package ruffkat.swing.statusbar;

import ruffkat.swing.ui.UndecoratedButton;

import javax.swing.AbstractAction;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

public class MemoryMeter extends UndecoratedButton {
    private final Runtime runtime = Runtime.getRuntime();
    private final Timer timer;

    private int updateInterval = 2000;
    private Color fillColor;

    public MemoryMeter() {
        setFillColor(UIManager.getColor("MemoryMeter.fillColor"));

        addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.gc();
                repaint();
            }
        });

        timer = new Timer(updateInterval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        ToolTipManager.sharedInstance().registerComponent(this);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
        timer.stop();
        timer.setDelay(updateInterval);
        timer.start();
    }

    public Dimension getPreferredSize() {
        Dimension base = super.getPreferredSize();
        FontMetrics metrics = getFontMetrics(getFont());
        long totalMemory = runtime.totalMemory();
        int messageWidth = metrics.stringWidth(meterText(totalMemory, totalMemory));
        Insets insets = getInsets();
        return new Dimension(messageWidth + base.width + insets.left + insets.right,
                metrics.getHeight() + insets.top + insets.bottom + 4);
    }

    public void paintComponent(Graphics g) {
        Dimension dimension = getSize();
        int usedWidth = (int) (dimension.width * usageMultiplier());
        g.setColor(getFillColor());
        g.fillRect(0, 1, usedWidth, dimension.height - 2);
        g.setColor(Color.gray);
        g.drawLine(usedWidth, 1, usedWidth, dimension.height - 2);
        g.drawRect(0, 1, dimension.width - 1, dimension.height - 2);
        super.paintComponent(g);
    }

    @Override
    public String getText() {
        return runtime != null ?
                meterText(runtime.freeMemory(), runtime.totalMemory()) :
                meterText(0, 0);
    }

    public String getToolTipText() {
        return toolTipText(runtime.freeMemory(), runtime.totalMemory());
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private String toolTipText(long freeMemory, long totalMemory) {
        return MessageFormat.format("Allocated: {0}M  Used: {1}M",
                Long.toString(totalMemory / 0x100000L),
                Long.toString((totalMemory - freeMemory) / 0x100000L));
    }

    private String meterText(long freeMemory, long totalMemory) {
        return MessageFormat.format("{0}M of {1}M",
                Long.toString((totalMemory - freeMemory) / 0x100000L),
                Long.toString(totalMemory / 0x100000L));
    }

    private double usageMultiplier() {
        double totalMemory = runtime.totalMemory();
        return ((totalMemory - runtime.freeMemory()) / totalMemory);
    }
}