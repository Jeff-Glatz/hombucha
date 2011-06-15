package ruffkat.hombucha.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Entity
public class Item
        extends Sourced {

    @Basic
    private BigDecimal price;

    @Basic
    private Currency currency = Currency.getInstance(Locale.getDefault());

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        if (!super.equals(o)) return false;

        Item item = (Item) o;

        if (currency != null ? !currency.equals(item.currency) : item.currency != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
