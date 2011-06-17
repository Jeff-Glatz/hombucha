package ruffkat.hombucha.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Money
        implements Serializable {
    private static final BigDecimal ZERO_AMOUNT = new BigDecimal("0.00");
    public static final Money ZERO = new Money();

    private final BigDecimal amount;
    private final Currency currency;

    public Money() {
        this(ZERO_AMOUNT, defaultCurrency());
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

    public static BigDecimal amount(String value) {
        return (value != null && value.length() > 0) ?
                new BigDecimal(value) : ZERO_AMOUNT;
    }
}
