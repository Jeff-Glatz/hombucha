package ruffkat.hombucha.money;

import ruffkat.hombucha.util.MathUtils;

import java.math.BigDecimal;
import java.util.Currency;

public class DefaultMoneyFormat
        implements MoneyFormat {
    private static final String[] EMPTY = new String[0];
    private static final DefaultMoneyFormat INSTANCE = new DefaultMoneyFormat();

    public static DefaultMoneyFormat get() {
        return INSTANCE;
    }

    public String format(Money money) {
        BigDecimal amount = money.getAmount();
        Currency currency = money.getCurrency();
        return new StringBuilder(amount.toPlainString()).
                append(" ").append(currency.getCurrencyCode()).toString();
    }

    public Money parse(String value) {
        String[] parts = value != null ? value.split(" ") : EMPTY;
        switch (parts.length) {
            case 1:
                return new Money(MathUtils.valueOf(parts[0]));
            case 2:
                return new Money(MathUtils.valueOf(parts[0]), Currency.getInstance(parts[1]));
            default:
                return Money.ZERO;
        }
    }
}
