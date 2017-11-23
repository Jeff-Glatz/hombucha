package ruffkat.hombucha.measure;

import javax.measure.Measure;
import javax.measure.MeasureFormat;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParsePosition;

@Converter
public class MeasureConverter
        implements AttributeConverter<Measure, String> {
    private final MeasureFormat format = MeasureFormat.getStandard();

    @Override
    public String convertToDatabaseColumn(Measure measure) {
        return measure != null ? format.format(measure) : null;
    }

    @Override
    public Measure convertToEntityAttribute(String value) {
        return value != null ? format.parse(value, new ParsePosition(0)) : null;
    }
}
