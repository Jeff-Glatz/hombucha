package ruffkat.hombucha.swing.sources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.model.Source;
import ruffkat.hombucha.store.RepositoryEvent;
import ruffkat.hombucha.store.RepositoryListener;
import ruffkat.hombucha.store.Sources;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

@Component
public class SourcesPanel
        extends JPanel
        implements RepositoryListener<Source> {
    @Autowired
    private Sources sources;

    @Autowired
    private SourceEditor editor;

    @PostConstruct
    public void initialize() {
        sources.addRepositoryListener(this);

        setLayout(new BorderLayout(5, 5));
        add(editor, BorderLayout.CENTER);
        add(makeNavigationPanel(), BorderLayout.SOUTH);
    }

    @PreDestroy
    public void destroy() {
        sources.removeRepositoryListener(this);
    }

    @Override
    public void saved(RepositoryEvent<Source> event) {

    }

    @Override
    public void deleted(RepositoryEvent<Source> event) {
    }

    private JComponent makeNavigationPanel() {
        Box panel = Box.createHorizontalBox();
        panel.add(Box.createHorizontalStrut(5));
        panel.add(new JButton("First"));
        panel.add(Box.createHorizontalGlue());
        panel.add(new JButton("Add"));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(new JButton("Delete"));
        panel.add(Box.createHorizontalGlue());
        panel.add(new JButton("Last"));
        panel.add(Box.createHorizontalStrut(5));
        return panel;
    }
}
