package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;
import ruffkat.hombucha.money.Money;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item<Q extends Quantity>
        extends Sourced {

    @Basic
    private String reference;

    @Basic
    @Type(type = "money")
    private Money price;

    @Basic
    @Type(type = "measure")
    private Measure<Q> unit;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Measure<Q> getUnit() {
        return unit;
    }

    public void setUnit(Measure<Q> unit) {
        this.unit = unit;
    }

    public Money unitPrice() {
        return price.divide(unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        if (!super.equals(o)) return false;

        Item item = (Item) o;

        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (reference != null ? !reference.equals(item.reference) : item.reference != null) return false;
        if (unit != null ? !unit.equals(item.unit) : item.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
