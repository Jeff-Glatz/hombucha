package ruffkat.hombucha.money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MoneyConverter
        implements AttributeConverter<Money, String> {
    private final MoneyFormat format = new DefaultMoneyFormat();

    @Override
    public String convertToDatabaseColumn(Money money) {
        return money != null ? format.format(money) : null;
    }

    @Override
    public Money convertToEntityAttribute(String value) {
        return value != null ? format.parse(value) : null;
    }
}
