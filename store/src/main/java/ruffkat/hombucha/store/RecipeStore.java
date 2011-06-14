package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Recipe;

public class RecipeStore
        extends AbstractStore<Recipe>
        implements Recipes {

    public RecipeStore() {
        super(Recipe.class);
    }

    @Override
    public Recipe create() {
        return new Recipe();
    }
}
