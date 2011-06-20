package ruffkat.swing.ui;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class Gutter extends JComponent implements DocumentListener, CaretListener {
    private final JEditorPane editor;

    private int numberGap;
    private int prototypeLineCount;

    public Gutter(JEditorPane editor) {
        this.editor = editor;
        this.numberGap = 5;
        this.prototypeLineCount = 9999;
        initialize();
    }

    public Font getFont() {
        return editor.getFont();
    }

    public Dimension getPreferredSize() {
        FontMetrics metrics = getFontMetrics(getFont());
        TextLayout layout = new TextLayout(String.valueOf(prototypeLineCount),
                metrics.getFont(), metrics.getFontRenderContext());
        Dimension base = super.getPreferredSize();
        base.setSize(base.width + numberGap + layout.getBounds().getWidth(), base.height);
        return base;
    }

    public int getPrototypeLineCount() {
        return prototypeLineCount;
    }

    public void setPrototypeLineCount(int prototypeLineCount) {
        this.prototypeLineCount = prototypeLineCount;
    }

    public int getNumberGap() {
        return numberGap;
    }

    public void setNumberGap(int numberGap) {
        this.numberGap = numberGap;
    }

    public void insertUpdate(DocumentEvent event) {
        repaint();
    }

    public void removeUpdate(DocumentEvent event) {
        repaint();
    }

    public void changedUpdate(DocumentEvent event) {
        repaint();
    }

    public void caretUpdate(CaretEvent event) {
        repaint();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        FontMetrics metrics = editor.getFontMetrics(editor.getFont());
        Dimension editorSize = editor.getSize();
        Point editorLocation = editor.getLocation();
        int start = editor.viewToModel(editorLocation);
        int end = editor.viewToModel(new Point(editorLocation.x + editorSize.width, editorLocation.y + editorSize.height));

        Element root = editor.getDocument().getDefaultRootElement();
        int firstLine = root.getElementIndex(start) + 1;
        int lastLine = root.getElementIndex(end) + 1;

        Rectangle selectionBounds = editorSelectionBounds();
        Dimension gutterSize = getSize();
        int lineHeight = metrics.getHeight();
        try {
            int yOffset = editor.modelToView(start).y;
            for (int line = firstLine; line <= lastLine; line++) {
                String number = String.valueOf(line);
                int numberWidth = metrics.stringWidth(number);
                int bottomY = (line * lineHeight) + yOffset;
                int topY = bottomY - lineHeight;
                if (selectionBounds != null && bottomY >= selectionBounds.getMaxY() && selectionBounds.getMinY() >= topY) {
                    Color originalColor = g2.getColor();
                    try {
                        g2.setColor(editor.getSelectionColor());
                        g2.fill(new Rectangle2D.Double(0, topY, gutterSize.width, lineHeight));
                        g2.setColor(editor.getSelectedTextColor());
                        g2.drawString(number, gutterSize.width - numberWidth - numberGap, bottomY - metrics.getDescent());
                    } finally {
                        g2.setColor(originalColor);
                    }
                } else {
                    g2.drawString(number, gutterSize.width - numberWidth - numberGap, bottomY - metrics.getDescent());
                }
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        setDoubleBuffered(true);
        setBackground(Color.LIGHT_GRAY);
        setForeground(new Color(128, 0, 0));
        setBorder(new StrokedBorder(new BasicStroke(1.0f),
                Textures.createVerticalPinstripe(Color.BLACK, Color.WHITE),
                StrokedBorder.Side.RIGHT));
        setFont(editor.getFont());
        setLayout(new BorderLayout());

        editor.addCaretListener(this);
        editor.getDocument().addDocumentListener(this);
    }

    private Rectangle editorSelectionBounds() {
        try {
            TextUI mapper = editor.getUI();
            return mapper.modelToView(editor, editor.getCaretPosition(), Position.Bias.Forward);
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
