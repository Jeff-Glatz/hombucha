package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Recipe
        extends Sourced {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ingredient<?>> ingredients = new LinkedList<Ingredient<?>>();

    @Basic
    private String instructions;

    @Type(type = "measure")
    private Measure<Volume> yields;

    public List<Ingredient<?>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient<?>> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Measure<Volume> getYields() {
        return yields;
    }

    public void setYields(Measure<Volume> yields) {
        this.yields = yields;
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
        if (yields != null ? !yields.equals(recipe.yields) : recipe.yields != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        result = 31 * result + (yields != null ? yields.hashCode() : 0);
        return result;
    }
}
