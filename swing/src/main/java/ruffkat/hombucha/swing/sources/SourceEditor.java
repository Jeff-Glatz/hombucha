package ruffkat.hombucha.swing.sources;

import org.springframework.stereotype.Component;
import ruffkat.hombucha.model.Source;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

@Component
public class SourceEditor extends JPanel {
    private Source source;

    @PostConstruct
    public void initialize() {

    }

    public void setSource(Source source) {
        this.source = source;
    }
}
