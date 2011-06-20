package ruffkat.hombucha.money;

import ruffkat.hombucha.util.MathUtils;
import ruffkat.hombucha.util.PropertyUtils;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Money
        implements Serializable {
    public static final Money ZERO = new Money();

    private final BigDecimal amount;
    private final Currency currency;

    public Money() {
        this(MathUtils.zero(), defaultCurrency());
    }

    public Money(String amount) {
        this(MathUtils.valueOf(amount), defaultCurrency());
    }

    public Money(BigDecimal amount) {
        this(amount, defaultCurrency());
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(BigDecimal that) {
        return new Money(MathUtils.add(amount, that), currency);
    }

    public Money add(Money that) {
        if (PropertyUtils.changed(currency, that.currency)) {
            throw new MismatchedCurrencyException(currency, that.currency);
        }
        return add(that.amount);
    }

    public Money subtract(BigDecimal that) {
        return new Money(MathUtils.subtract(amount, that), currency);
    }

    public Money subtract(Money that) {
        if (PropertyUtils.changed(currency, that.currency)) {
            throw new MismatchedCurrencyException(currency, that.currency);
        }
        return subtract(that.amount);
    }

    public <Q extends Quantity> Money multiply(Measure<Q> measure) {
        BigDecimal value = MathUtils.valueOf(measure.getValue());
        return new Money(MathUtils.multiply(amount, value), currency);
    }

    public <Q extends Quantity> Money divide(Measure<Q> measure) {
        BigDecimal value = MathUtils.valueOf(measure.getValue());
        return new Money(MathUtils.divide(amount, value), currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money = (Money) o;

        if (amount != null ? !amount.equals(money.amount) : money.amount != null) return false;
        if (currency != null ? !currency.equals(money.currency) : money.currency != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public static Currency defaultCurrency() {
        return Currency.getInstance(Locale.getDefault());
    }
}
