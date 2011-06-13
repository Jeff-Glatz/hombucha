package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Recipe;

public interface Recipes {
    Recipe create();
    Recipe load(Long id);
    void delete(Long id);
}
