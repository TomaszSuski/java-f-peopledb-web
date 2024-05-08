package com.lingarogroup.peopledbweb.web.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateFormatter implements Formatter<LocalDate> {

//    private final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
//    private final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    returning to the default ISO_LOCAL_DATE format to be able to fill in the form for editing
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, formatter);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return formatter.format(object);
    }
}
