package com.lingarogroup.peopledbweb.web.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Locale;

@Component
public class MultiPartFileFormatter implements Formatter<MultipartFile> {
    @Override
    public MultipartFile parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(MultipartFile object, Locale locale) {
        return object.getOriginalFilename();
    }
}
