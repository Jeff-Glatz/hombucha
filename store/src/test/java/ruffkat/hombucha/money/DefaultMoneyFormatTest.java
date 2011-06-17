package ruffkat.hombucha.money;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static junit.framework.Assert.assertEquals;

public class DefaultMoneyFormatTest {
    private DefaultMoneyFormat format;

    @Before
    public void setUp()
            throws Exception {
        format = new DefaultMoneyFormat();
    }

    @Test
    public void testFormatting()
            throws Exception {
        assertEquals("1.25 USD", format.format(new Money(new BigDecimal("1.25"))));
        assertEquals("1.25 JPY", format.format(new Money(new BigDecimal("1.25"),
                Currency.getInstance("JPY"))));
    }

    @Test
    public void testParsing()
            throws Exception {
        assertEquals(new Money(), format.parse(null));
        assertEquals(new Money(), format.parse(""));
        assertEquals(new Money(new BigDecimal("1.25")), format.parse("1.25"));
        assertEquals(new Money(new BigDecimal("1.25")), format.parse("1.25 USD"));
        assertEquals(new Money(new BigDecimal("1.25"),
                Currency.getInstance("JPY")), format.parse("1.25 JPY"));
    }
}
