package ruffkat.hombucha.swing.vessels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Vessels;

import javax.swing.JPanel;

@Component
public class VesselsPanel extends JPanel {

    @Autowired
    private Vessels vessels;
}
