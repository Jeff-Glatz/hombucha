package ruffkat.hombucha.swing.sources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Sources;

import javax.swing.JPanel;

@Component
public class SourcesPanel extends JPanel {
    @Autowired
    private Sources sources;
}
