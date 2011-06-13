package ruffkat.hombucha.model;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
public class Ingredient<Q extends Quantity> implements Serializable {
    @OneToOne
    private Item item;
    @Transient
    private Measure<Q> amount;
    private String instructions;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Measure<Q> getAmount() {
        return amount;
    }

    public void setAmount(Measure<Q> amount) {
        this.amount = amount;
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

        Ingredient that = (Ingredient) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (instructions != null ? !instructions.equals(that.instructions) : that.instructions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }
}
