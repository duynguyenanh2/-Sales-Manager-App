package helper;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;

public class XDate {

    static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                sdf.applyPattern(pattern[0]);
            }
            if (date == null) {
                return XDate.now();
            }
            return sdf.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            sdf.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = XDate.now();
        }
        return sdf.format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static Date add(int days) {
        Date now = new Date();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now;
    }

    public static Date addDays(Date date, int days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static Date addJ(int days) {
        Date now = new Date();
        DateTime dt = new DateTime(now);
        DateTime datePlus = dt.plusDays(days);
        return datePlus.toDate();
    }

    public static Date addDaysJ(Date date, int days) {
        DateTime dt = new DateTime(date);
        DateTime datePlus = dt.plusDays(days);
        return datePlus.toDate();
    }

    public static long getDaysBetween(Date date1, Date date2) {
        DateTime start = new DateTime(date1);
        DateTime end = new DateTime(date2);
        long days = Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays();
        return days;
    }

    public static int getYearsBetween(Date date1, Date date2) {
        DateTime start = new DateTime(date1);
        DateTime end = new DateTime(date2);
        int years = Years.yearsBetween(start.toLocalDate(), end.toLocalDate()).getYears();
        return years;
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillis = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static boolean isDateValid(String date) {
        try {
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLegalDate(String date) {
        sdf.setLenient(false);
        return sdf.parse(date, new ParsePosition(0)) != null;
    }
}
