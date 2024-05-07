package com.lingarogroup.peopledbweb.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private BigDecimal salary;

    public String getFormattedDateOfBirth() {
        return DateTimeFormatter.ofPattern("MM/dd/yyyy").format(dateOfBirth);
    }
}
