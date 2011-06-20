package ruffkat.hombucha.money;

import java.util.Currency;

public class MismatchedCurrencyException
        extends RuntimeException {
    private final Currency expected;
    private final Currency actual;

    public MismatchedCurrencyException(Currency expected, Currency actual) {
        this.expected = expected;
        this.actual = actual;
    }

    public Currency getExpected() {
        return expected;
    }

    public Currency getActual() {
        return actual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MismatchedCurrencyException that = (MismatchedCurrencyException) o;

        if (actual != null ? !actual.equals(that.actual) : that.actual != null) return false;
        if (expected != null ? !expected.equals(that.expected) : that.expected != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expected != null ? expected.hashCode() : 0;
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        return result;
    }
}
