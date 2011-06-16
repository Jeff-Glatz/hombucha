package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Item;
import ruffkat.hombucha.model.Recipe;

import javax.measure.quantity.Mass;

@Component
public class RecipeMaker
        extends AbstractMaker<Recipe> {

    @Autowired
    private Recipes recipes;

    @Autowired
    private Items items;

    public RecipeMaker() {
        super(Recipe.class);
    }

    @Override
    public Recipes repository() {
        return recipes;
    }

    @Override
    public void make()
            throws Exception {
        Item<Mass> sugar = Searches.first(items, "Sugar in the Raw");
        Item<Mass> tea = Searches.first(items, "Organic Ancient Emerald Lily");

        Recipe starter = recipes.create();
        starter.setName("Starter Solution");
        starter.setYields(Measurements.volume("6.0 l"));
        starter.setInstructions("Boil water, steep tea, add sugar, cool down");
        starter.addIngredient(new Ingredient<Mass>(sugar, Measurements.mass("500 g")));
        starter.addIngredient(new Ingredient<Mass>(tea, Measurements.mass("10 g")));
        recipes.save(starter);

        Recipe feeder = recipes.create();
        feeder.setName("Feeder Solution");
        feeder.setYields(Measurements.volume("6.0 l"));
        feeder.setInstructions("Boil water, steep tea, add sugar, cool down");
        feeder.addIngredient(new Ingredient<Mass>(sugar, Measurements.mass("500 g")));
        feeder.addIngredient(new Ingredient<Mass>(tea, Measurements.mass("10 g")));
        recipes.save(feeder);
    }
}
