package ruffkat.hombucha.swing.main;

import org.jdesktop.swingx.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.store.Ferments;
import ruffkat.hombucha.store.Items;
import ruffkat.hombucha.store.Mothers;
import ruffkat.hombucha.store.Recipes;
import ruffkat.hombucha.store.Sources;
import ruffkat.hombucha.store.Vessels;

import java.util.regex.Pattern;

@Component
public class SearchableSchema
        implements Searchable {

    @Autowired
    private Sources sources;

    @Autowired
    private Items items;

    @Autowired
    private Vessels vessels;

    @Autowired
    private Mothers mothers;

    @Autowired
    private Recipes recipes;

    @Autowired
    private Ferments ferments;

    @Override
    public int search(String s) {
        return 0;
    }

    @Override
    public int search(String s, int i) {
        return 0;
    }

    @Override
    public int search(String s, int i, boolean b) {
        return 0;
    }

    @Override
    public int search(Pattern pattern) {
        return 0;
    }

    @Override
    public int search(Pattern pattern, int i) {
        return 0;
    }

    @Override
    public int search(Pattern pattern, int i, boolean b) {
        return 0;
    }
}
