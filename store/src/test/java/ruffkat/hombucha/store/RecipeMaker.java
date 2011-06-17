package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Item;
import ruffkat.hombucha.model.Recipe;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

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
        Item<Volume> water = Searches.first(items, "Distilled Water");

        Recipe starter = recipes.create();
        starter.setName("Starter Solution");
        starter.setVolume(Measurements.volume("3.785 l"));
        starter.setInstructions(
                "1. Boil water (no longer than five minutes)\n" +
                        "2. Steep tea accordingly\n" +
                        "3. Add sugar\n " +
                        "4. Cool down to room temperature\n" +
                        "5. Add 0.5 l of starter ferment");
        starter.addIngredient(new Ingredient<Volume>(water, Measurements.volume("2.84 l")));
        starter.addIngredient(new Ingredient<Mass>(sugar, Measurements.mass("250 g")));
        starter.addIngredient(new Ingredient<Mass>(tea, Measurements.mass("5 g")));
        recipes.save(starter);

        Recipe feeder = recipes.create();
        feeder.setName("Feeder Solution");
        feeder.setVolume(Measurements.volume("1.0 l"));
        feeder.setInstructions(
                "1. Boil water (no longer than five minutes)\n" +
                        "2. Steep tea accordingly\n" +
                        "3. Add sugar\n " +
                        "4. Cool down to room temperature");
        feeder.addIngredient(new Ingredient<Volume>(water, Measurements.volume("1.0 l")));
        feeder.addIngredient(new Ingredient<Mass>(sugar, Measurements.mass("85 g")));
        feeder.addIngredient(new Ingredient<Mass>(tea, Measurements.mass("5 g")));
        recipes.save(feeder);
    }
}
