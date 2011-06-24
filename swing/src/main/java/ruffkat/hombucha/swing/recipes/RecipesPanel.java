package ruffkat.hombucha.swing.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Recipes;

import javax.swing.JPanel;

@Component
public class RecipesPanel extends JPanel {
    @Autowired
    private Recipes recipes;
}
