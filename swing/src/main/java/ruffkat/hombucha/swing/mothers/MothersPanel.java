package ruffkat.hombucha.swing.mothers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Mothers;

import javax.swing.JPanel;

@Component
public class MothersPanel extends JPanel {
    @Autowired
    private Mothers mothers;
}
