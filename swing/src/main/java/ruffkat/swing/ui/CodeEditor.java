package ruffkat.swing.ui;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.FocusManager;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Reader;

/**
 * A very simple code/script editor with gutter that displays line numbers
 * and supports contextual assistance through code hints
 *
 * @param <T> The concrete {@link JEditorPane} used to edit text
 */
public abstract class CodeEditor<T extends JEditorPane>
        extends JPanel
        implements KeyEventDispatcher {
    protected final JPopupMenu hintsPopup = new JPopupMenu();
    protected final DefaultListModel hintsListModel = new DefaultListModel();
    protected final JList hintsList = new JList(hintsListModel);
    protected final ErrorHandler errorHandler;
    protected final T pane;
    protected final Gutter gutter;

    public CodeEditor(ErrorHandler errorHandler, T pane) {
        this.errorHandler = errorHandler;
        this.pane = pane;
        this.gutter = new Gutter(pane);
    }

    public T getPane() {
        return pane;
    }

    public Document getDocument() {
        return pane.getDocument();
    }

    /**
     * Configures this {@link CodeEditor} for read-only display of text
     */
    public void setReadOnly() {
        pane.setEditable(false);
        pane.setAutoscrolls(true);
    }

    /**
     * Sets the {@link JPopupMenu} presented to the user
     *
     * @param popupMenu The {@link JPopupMenu} presented to the user
     */
    public void setContextMenu(JPopupMenu popupMenu) {
        pane.addMouseListener(new PopupMenuTrigger(popupMenu));
    }

    public void setFindAction(Action find) {
        // TODO: this needs work
        pane.getActionMap().put("find", find);
    }

    /**
     * Prepares this {@link CodeEditor} for use, must be called
     *
     * @see #configureEditor()
     */
    public void initialize() {
        hintsList.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        hintsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hintsList.setLayoutOrientation(JList.VERTICAL);
        hintsList.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(hintsList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setWheelScrollingEnabled(true);

        hintsPopup.add(scrollPane);
        hintsPopup.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        pane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

        configureEditor();

        setLayout(new BorderLayout());
        add(gutter, BorderLayout.WEST);
        add(pane, BorderLayout.CENTER);
    }

    /**
     * Reads the contents of the specified {@link Reader} into the current
     * {@link Document}
     *
     * @param reader The {@link Reader} to read from
     * @throws IOException          If an error occurs while reading
     * @throws BadLocationException If an error occurs while parsing the document
     */
    public void read(Reader reader)
            throws IOException, BadLocationException {
        EditorKit kit = pane.getEditorKit();
        Document document = pane.getDocument();
        kit.read(reader, document, 0);
    }

    /**
     * Clears the contents of the current {@link Document}
     *
     * @throws BadLocationException If an error occurs while clearing the document
     */
    public void clear()
            throws BadLocationException {
        Document document = pane.getDocument();
        document.remove(0, document.getLength());
    }

    /**
     * Adds this {@link CodeEditor}, as a {@link KeyEventDispatcher},
     * to the {@link FocusManager#getCurrentKeyboardFocusManager()}
     */
    public void addKeyEventDispatcher() {
        KeyboardFocusManager focusManager =
                FocusManager.getCurrentKeyboardFocusManager();
        focusManager.addKeyEventDispatcher(this);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            if (hintsRequested(event)) {
                try {
                    hintsListModel.setSize(0);
                    if (hintsAvailable()) {
                        int suggestions = hintsListModel.getSize();
                        int caretPosition = pane.getCaretPosition();
                        Rectangle area = pane.modelToView(caretPosition);
                        hintsList.setVisibleRowCount(suggestions < 8 ? suggestions : 8);
                        hintsPopup.show(this, area.x, area.y + area.height);
                        hintsList.requestFocus();
                        return true;
                    }
                } catch (Exception e) {
                    errorHandler.onError("Failure displaying code hints", e);
                }
            } else if (hintSelected(event)) {
                try {
                    try {
                        Object value = hintsList.getSelectedValue();
                        if (value != null) {
                            hintSelected((String) value);
                            return true;
                        }
                    } finally {
                        hintsPopup.setVisible(false);
                    }
                } catch (Exception e) {
                    errorHandler.onError("Failure applying selected hint", e);
                }
            }
        }
        return false;
    }

    /**
     * Removes this {@link CodeEditor}, as a {@link KeyEventDispatcher},
     * from the {@link FocusManager#getCurrentKeyboardFocusManager()}
     */
    public void removeKeyEventDispatcher() {
        KeyboardFocusManager focusManager =
                FocusManager.getCurrentKeyboardFocusManager();
        focusManager.removeKeyEventDispatcher(this);
    }

    /**
     * Template method invoked during {@link KeyEvent} processing
     * to determine if the intention is to request code hints for
     * the current selection/caret position
     *
     * @param event The {@link KeyEvent} to process
     * @return {@code true} if the intention of the event is to
     *         request code hints; {@code false} otherwise
     */
    protected boolean hintsRequested(KeyEvent event) {
        return event.isControlDown() &&
                event.getKeyCode() == KeyEvent.VK_SPACE;
    }

    /**
     * Template method invoked during {@link KeyEvent} processing
     * to determine if the intention is to signal a hint has
     * been selected
     *
     * @param event The {@link KeyEvent} to process
     * @return {@code true} if the intention of the event is to
     *         indicate hint selection; {@code false} otherwise
     */
    protected boolean hintSelected(KeyEvent event) {
        return hintsPopup.isVisible() &&
                event.getKeyCode() == KeyEvent.VK_ENTER;
    }

    /**
     * Invoked from {@link #initialize()} providing subclasses an
     * opportunity perform additional configuration to the editor
     * before initialization completes
     */
    protected abstract void configureEditor();

    /**
     * Invoked when the user has requested contextual assistance to
     * determine if any is available
     *
     * @return {@code true} if contextual assistance is available;
     *         {@code false} otherwise
     * @throws IOException          If an error occurs while determining the hints
     * @throws BadLocationException If an error occurs while determining the hints
     */
    protected abstract boolean hintsAvailable()
            throws IOException, BadLocationException;

    /**
     * Invoked when the user has selected a hint presented after
     * contextual assistance was requested
     *
     * @param hint The hint selected by the user
     * @throws IOException          If an error occurs while applying the change
     * @throws BadLocationException If an error occurs while applying the change
     */
    protected abstract void hintSelected(String hint)
            throws IOException, BadLocationException;
}
