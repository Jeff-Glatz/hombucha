package ruffkat.hombucha.money;

public interface MoneyFormat {
    String format(Money money);
    Money parse(String value);
}
