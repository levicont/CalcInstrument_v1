/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Victor
 */
public class DateUtil {
    
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2000, Month.JANUARY, 1);
    public static final LocalDate TODAY = LocalDate.now();


    public static LocalDate getLocalDate(Date date) {
        if (date == null)
            return null;
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date getDate(LocalDate localDate) {
        if (localDate == null)
            return null;
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    public static String format(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return DATE_FORMATTER.format(localDate);
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return SIMPLE_DATE_FORMAT.format(date);
    }    
    
}
