package ruffkat.hombucha.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MathUtils {
    private static final MathContext CONTEXT = new MathContext(4, RoundingMode.HALF_UP);
    private static final BigDecimal ZERO = new BigDecimal("0.000", CONTEXT);

    public static MathContext context() {
        return CONTEXT;
    }

    public static BigDecimal zero() {
        return ZERO;
    }

    public static BigDecimal valueOf(String value) {
        return (value != null && value.length() > 0) ?
                new BigDecimal(value, CONTEXT) : zero();
    }

    public static BigDecimal valueOf(Number value) {
        return new BigDecimal(value.doubleValue(), CONTEXT);
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b, CONTEXT);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b, CONTEXT);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b, CONTEXT);
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, CONTEXT);
    }
}
