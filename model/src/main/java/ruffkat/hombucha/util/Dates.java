package ruffkat.hombucha.util;

import java.util.Calendar;
import java.util.Date;

public final class Dates {

    public static Date date(int month, int day, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }
}
