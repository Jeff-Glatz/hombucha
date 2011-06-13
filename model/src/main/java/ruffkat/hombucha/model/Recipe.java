package ruffkat.hombucha.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class Recipe implements Serializable, Sourced {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Transient
    private Source source;
    private Calendar received;
    @ElementCollection
    private List<Ingredient<?>> ingredients;
    private String instructions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Calendar getReceived() {
        return received;
    }

    public void setReceived(Calendar received) {
        this.received = received;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        if (instructions != null ? !instructions.equals(recipe.instructions) : recipe.instructions != null)
            return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (received != null ? !received.equals(recipe.received) : recipe.received != null) return false;
        if (source != null ? !source.equals(recipe.source) : recipe.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (received != null ? received.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }
}
