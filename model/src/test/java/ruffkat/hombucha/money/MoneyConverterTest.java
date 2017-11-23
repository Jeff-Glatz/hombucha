package ruffkat.hombucha.money;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyConverterTest {
    private MoneyConverter converter;

    @Before
    public void setUp()
            throws Exception {
        converter = new MoneyConverter();
    }

    @Test
    public void shouldConvertMoneyToString()
            throws Exception {
        assertThat(converter.convertToDatabaseColumn(new Money("1.25", "JPY")))
                .isEqualTo("1.25 JPY");
        assertThat(converter.convertToDatabaseColumn(null))
                .isNull();
    }

    @Test
    public void shouldConvertStringToMoney()
            throws Exception {
        assertThat(converter.convertToEntityAttribute("1.25 JPY"))
                .isEqualTo(new Money("1.25", "JPY"));
        assertThat(converter.convertToEntityAttribute(null))
                .isNull();
    }
}
