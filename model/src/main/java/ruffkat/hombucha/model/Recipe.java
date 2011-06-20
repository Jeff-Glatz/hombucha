package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ruffkat.hombucha.measure.MeasureBridge;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.money.Money;
import ruffkat.hombucha.money.Priced;
import ruffkat.hombucha.util.PropertyUtils;

import javax.measure.Measure;
import javax.measure.converter.MultiplyConverter;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Volume;
import javax.measure.unit.Unit;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Indexed
public class Recipe
        extends Sourced
        implements Volumetric, Priced {

    @ElementCollection(fetch = FetchType.EAGER)
    @IndexedEmbedded
    private List<Ingredient<?>> ingredients = new LinkedList<Ingredient<?>>();

    @Basic
    @Field
    private String instructions;

    @Basic
    @Field
    @FieldBridge(impl = MeasureBridge.class)
    @Type(type = "measure")
    private Measure<Volume> volume;

    public List<Ingredient<?>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient<?>> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient<?> ingredient) {
        ingredients.add(ingredient);
    }

    public int ingredientCount() {
        return ingredients.size();
    }

    /**
     * Locates and returns the {@link Ingredient} with the specified name
     *
     * @param name The name of the ingredient
     * @param <Q>  The ingredients {@link Quantity} of measure
     * @return The named {@link Ingredient}; {@code null} if not found
     */
    public <Q extends Quantity> Ingredient<Q> ingredient(String name) {
        for (Ingredient<?> ingredient : ingredients) {
            Item<? extends Quantity> item = ingredient.getItem();
            if (PropertyUtils.same(name, item.getName())) {
                return (Ingredient<Q>) ingredient;
            }
        }
        return null;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    /**
     * Scales this {@link Recipe recipe} by the specified factor. For
     * example:
     * <p/>
     * <ul>
     * <li>To halve a recipe, the factor would be {@code 0.5}</li>
     * <li>To double a recipe, the factor would be {@code 2.0}</li>
     * </ul>
     *
     * @param factor The factor by which this recipe should be
     *               scaled.
     * @return The scaled {@link Recipe}
     */
    public Recipe scale(float factor) {
        MultiplyConverter converter = new MultiplyConverter(factor);
        Recipe recipe = new Recipe();
        recipe.setInstructions(getInstructions());
        recipe.setName(getName());
        recipe.setVolume(Measurements.convert(getVolume(), converter));
        recipe.setReceived(getReceived());
        recipe.setSource(getSource());
        List<Ingredient<?>> scaled = new ArrayList<Ingredient<?>>(ingredientCount());
        for (Ingredient<?> ingredient : ingredients) {
            scaled.add(ingredient.scale(converter));
        }
        recipe.setIngredients(scaled);
        return recipe;
    }

    /**
     * Scales this {@link Recipe} to yield the desired volume
     *
     * @param yield The desired output of the recipe, in terms of volume
     * @return The scaled {@link Recipe}
     */
    public Recipe scale(Measure<Volume> yield) {
        Unit<Volume> unit = volume.getUnit();
        float current = volume.floatValue(unit);
        float requested = yield.floatValue(unit);
        return scale(1.0f + ((requested - current) / current));
    }

    /**
     * Scales this {@link Recipe} according to the specified amount of a
     * specific ingredient
     *
     * @param name   The ingredient upon which the scaled recipe will be derived
     * @param amount The new amount of the ingredient
     * @param <Q>    The {@link Quantity} being measured
     * @return The scaled {@link Recipe}
     */
    public <Q extends Quantity> Recipe scale(String name, Measure<Q> amount) {
        Ingredient<Q> ingredient = ingredient(name);
        Unit<Q> unit = ingredient.getAmount().getUnit();
        float current = ingredient.getAmount().floatValue(unit);
        float requested = amount.floatValue(unit);
        return scale(1.0f + ((requested - current) / current));
    }

    public Money price() {
        Money price = Money.ZERO;
        for (Ingredient<?> ingredient : ingredients) {
            price = price.add(ingredient.price());
        }
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        if (!super.equals(o)) return false;

        Recipe recipe = (Recipe) o;

        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        if (instructions != null ? !instructions.equals(recipe.instructions) : recipe.instructions != null)
            return false;
        if (volume != null ? !volume.equals(recipe.volume) : recipe.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", volume=" + volume +
                '}';
    }
}
