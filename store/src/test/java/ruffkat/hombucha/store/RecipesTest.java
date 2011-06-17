package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Friend;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Item;
import ruffkat.hombucha.model.Online;
import ruffkat.hombucha.model.Recipe;
import ruffkat.hombucha.money.Money;
import ruffkat.hombucha.time.Dates;

import javax.measure.quantity.Mass;
import javax.persistence.EntityNotFoundException;
import java.net.URL;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class RecipesTest extends FunctionalTest {
    @Autowired
    private Recipes recipes;

    @Test
    @Rollback(false)
    public void testSaveAndLoad()
            throws Exception {
        Friend friend = new Friend("Zoid Berg");
        friend.setEmail("foo@bar.com");
        entityManager.persist(friend);

        Online westelm = new Online("Recycled Glass Beverage Dispenser",
                new URL("http://www.westelm.com"));
        entityManager.persist(westelm);

        Online rishi = new Online("Rishi Tea Company",
                new URL("http://www.rishi.com"));
        entityManager.persist(rishi);

        Item<Mass> item = new Item<Mass>();
        item.setName("Ancient Emerald Lilly");
        item.setSource(rishi);
        item.setUnit(Measurements.mass("125 g"));
        item.setPrice(new Money("19.99"));

        entityManager.persist(item);

        Recipe recipe = recipes.create();
        recipe.setName("SCOBY DO");
        recipe.setReceived(Dates.date(Calendar.MAY, 12, 2011));
        recipe.setVolume(Measurements.volume("4.0 L"));
        recipe.setSource(friend);

        Ingredient<Mass> tea = new Ingredient<Mass>();
        tea.setAmount(Measurements.mass("5.0 g"));
        tea.setInstructions("Let stand for 5 minutes");
        tea.setItem(item);

        recipe.addIngredient(tea);

        entityManager.persist(recipe);

        Long id = recipe.getOid();

        assertNotNull(id);
        assertEquals(recipe, recipes.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Recipe recipe = recipes.create();
        recipe.setName("SCOBY DO");
        recipe.setReceived(Dates.date(Calendar.MAY, 12, 2011));
        recipe.setVolume(Measurements.volume("2.0 L"));

        entityManager.persist(recipe);

        Long id = recipe.getOid();

        recipe = recipes.load(id);
        recipes.delete(recipe);

        try {
            recipes.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }

    public static void print(Recipe scaled) {
        System.out.println("Recipe: " + scaled.getName());
        System.out.println("Yield: " + scaled.getVolume());
        System.out.println();
        System.out.println("Ingredient List");
        for (Ingredient<?> ingredient : scaled.getIngredients()) {
            System.out.print("    * ");
            System.out.print(ingredient.getItem().getName());
            System.out.print(" - ");
            System.out.println(ingredient.getAmount());
        }
        System.out.println();
        System.out.println("Instructions");
        System.out.println(scaled.getInstructions());
    }
}
