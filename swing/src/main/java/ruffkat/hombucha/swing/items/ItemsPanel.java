package ruffkat.hombucha.swing.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Items;

import javax.swing.JPanel;

@Component
public class ItemsPanel extends JPanel {
    @Autowired
    private Items items;
}
