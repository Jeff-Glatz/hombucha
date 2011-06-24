package ruffkat.hombucha.swing.ferments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Ferments;

import javax.swing.JPanel;

@Component
public class FermentsPanel extends JPanel {
    @Autowired
    private Ferments ferments;
}
