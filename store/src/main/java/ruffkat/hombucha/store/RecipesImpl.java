package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Recipe;

public class RecipesImpl
        extends AbstractStore<Recipe>
        implements Recipes {

    public RecipesImpl() {
        super(Recipe.class);
    }

    @Override
    public Recipe create() {
        return new Recipe();
    }
}
