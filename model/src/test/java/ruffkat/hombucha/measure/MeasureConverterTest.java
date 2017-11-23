package ruffkat.hombucha.measure;

import org.junit.Before;
import org.junit.Test;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.measure.unit.Unit;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class MeasureConverterTest {
    private MeasureConverter converter;

    @Before
    public void setUp()
            throws Exception {
        converter = new MeasureConverter();
    }

    @Test
    public void shouldConvertMeasureToString()
            throws Exception {
        assertThat(converter.convertToDatabaseColumn(Measurements.volume("6.0 l")))
                .isEqualTo("6.0 l");
        assertThat(converter.convertToDatabaseColumn(null))
                .isNull();
    }

    @Test
    public void shouldConvertStringToMeasure()
            throws Exception {
        assertThat(converter.convertToEntityAttribute("6.0 l"))
                .isEqualTo(Measurements.volume("6.0 l"));
        assertThat(converter.convertToEntityAttribute(null))
                .isNull();
    }

    @Test
    public void testFormatting()
            throws Exception {
        Unit<Volume> unit = Units.volume("L");
        Measure<Volume> volume = Measurements.volume("6.0 l");

        assertEquals(new BigDecimal("6.0"), volume.getValue());
        assertEquals(unit, volume.getUnit());
        assertEquals("6.0 l", volume.toString());
    }
}
