package ruffkat.hombucha.model;

import java.util.Calendar;

public final class CalendarUtils {

    public static Calendar date(int month, int day, int year) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.YEAR, year);
        return date;
    }
}
