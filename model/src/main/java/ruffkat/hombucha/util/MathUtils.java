package ruffkat.hombucha.util;

import java.math.BigDecimal;
import java.math.MathContext;

public class MathUtils {
    private static final MathContext CONTEXT = MathContext.DECIMAL32;
    private static final BigDecimal ZERO = new BigDecimal("0.000000", CONTEXT);
    private static final BigDecimal ONE = new BigDecimal("1.000000", CONTEXT);

    public static MathContext context() {
        return CONTEXT;
    }

    public static BigDecimal zero() {
        return ZERO;
    }

    public static BigDecimal one() {
        return ONE;
    }

    public static BigDecimal valueOf(String value) {
        return (value != null && value.length() > 0) ?
                new BigDecimal(value, CONTEXT) : ZERO;
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

    public static BigDecimal scaleFactor(BigDecimal original, BigDecimal requested) {
        return ONE.add(requested.subtract(original).divide(original, CONTEXT));
    }
}
