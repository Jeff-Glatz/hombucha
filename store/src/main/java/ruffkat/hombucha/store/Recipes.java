package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Recipe;

public interface Recipes
        extends Repository<Recipe> {
    Recipe create();
}
