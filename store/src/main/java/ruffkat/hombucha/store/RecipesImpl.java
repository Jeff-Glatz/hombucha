package ruffkat.hombucha.store;

import org.springframework.stereotype.Repository;
import ruffkat.hombucha.model.Recipe;

@Repository
public class RecipesImpl
        extends AbstractRepository<Recipe>
        implements Recipes {

    public RecipesImpl() {
        super(Recipe.class);
    }

    @Override
    public Recipe create() {
        return new Recipe();
    }
}
