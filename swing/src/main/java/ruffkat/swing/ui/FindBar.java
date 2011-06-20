package ruffkat.swing.ui;

import org.jdesktop.swingx.JXFindBar;
import org.jdesktop.swingx.action.AbstractActionExt;
import org.jdesktop.swingx.search.NativeSearchFieldSupport;
import org.jdesktop.swingx.search.PatternModel;
import org.jdesktop.swingx.search.RecentSearches;
import org.jdesktop.swingx.search.Searchable;
import ruffkat.swing.action.ActionConfiguration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FindBar extends JXFindBar {
    private final ActionConfiguration actionConfiguration;
    private final RecentSearches searches;

    private JButton findRecent;
    private JCheckBox incremental;
    private JButton close;

    public FindBar(ActionConfiguration actionConfiguration, Searchable searchable, RecentSearches searches) {
        super(searchable);
        this.actionConfiguration = actionConfiguration;
        this.searches = searches;
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        findNext = new UndecoratedButton();
        findPrevious = new UndecoratedButton();
        findRecent = new UndecoratedButton();
        incremental = new JCheckBox();
        close = new UndecoratedButton();
        searches.install(searchField);
    }

    @Override
    protected void bind() {
        super.bind();

        MouseListener rollover = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                AbstractButton button = (AbstractButton) e.getSource();
                if (button.isEnabled()) {
                    button.setBorderPainted(true);
                }
            }

            public void mouseExited(MouseEvent e) {
                AbstractButton button = (AbstractButton) e.getSource();
                if (button.isEnabled()) {
                    button.setBorderPainted(false);
                }
            }
        };

        // configure find next
        findNext.addMouseListener(rollover);
        findNext.setHideActionText(true);
        findNext.setRolloverEnabled(true);
        actionConfiguration.configure("find.next", findNext.getAction());
        findNext.setPreferredSize(new Dimension(20, 20));

        // configure find previous
        findPrevious.addMouseListener(rollover);
        findPrevious.setHideActionText(true);
        findPrevious.setRolloverEnabled(true);
        actionConfiguration.configure("find.previous", findPrevious.getAction());
        findPrevious.setPreferredSize(new Dimension(20, 20));

        // configure find recent
        findRecent.addMouseListener(rollover);
        findRecent.setHideActionText(true);
        findRecent.setRolloverEnabled(true);
        findRecent.setAction(actionConfiguration.configure("find.recent", new AbstractActionExt() {
            public void actionPerformed(ActionEvent e) {
                showRecentSearches();
            }
        }));
        findRecent.setPreferredSize(new Dimension(20, 20));

        // configure case sensitive search
        matchCheck.setOpaque(false);
        matchCheck.setFocusable(false);
        matchCheck.setFocusPainted(false);
        actionConfiguration.configure("find.case", matchCheck.getAction());
        matchCheck.getModel().setSelected(getPatternModel().isCaseSensitive());

        // configure incremental search
        incremental.setOpaque(false);
        incremental.setFocusable(false);
        incremental.setFocusPainted(false);
        incremental.setAction(actionConfiguration.configure("find.incremental", new AbstractActionExt() {
            public void actionPerformed(ActionEvent e) {
                toggleIncrementalSearch();
            }
        }));
        incremental.getModel().setSelected(getPatternModel().isIncremental());

        // configure close search bar button
        close.setHideActionText(true);
        close.setRolloverEnabled(true);
        close.setAction(actionConfiguration.configure("find.close", new AbstractActionExt() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        }));
        close.setPressedIcon(UIManager.getIcon("close.pressed.icon"));
        close.setRolloverIcon(UIManager.getIcon("close.rollover.icon"));
        close.setPreferredSize(new Dimension(20, 20));
    }

    @Override
    protected void build() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        add(searchLabel);
        add(Box.createHorizontalStrut(5));
        add(searchField);
        add(Box.createHorizontalStrut(5));
        add(findRecent);
        add(Box.createHorizontalStrut(5));
        add(findNext);
        add(Box.createHorizontalStrut(5));
        add(findPrevious);
        add(Box.createHorizontalStrut(5));
        add(matchCheck);
        add(Box.createHorizontalStrut(5));
        add(incremental);
        add(Box.createHorizontalGlue());
        add(close);
    }

    private void showRecentSearches() {
        JPopupMenu popup = NativeSearchFieldSupport.getFindPopupMenu(searchField);
        popup.show(findRecent, 0, findRecent.getY() + findRecent.getHeight());
    }

    private void toggleIncrementalSearch() {
        PatternModel model = getPatternModel();
        model.setIncremental(!model.isIncremental());
    }
}
