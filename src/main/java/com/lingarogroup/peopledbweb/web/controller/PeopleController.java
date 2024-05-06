package com.lingarogroup.peopledbweb.web.controller;

import com.lingarogroup.peopledbweb.biz.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/people")
public class PeopleController {

    @GetMapping
    public String getPeople(Model model) {
        List<Person> people = List.of(
                new Person(1L, "John", "Doe", LocalDate.of(1980, 1, 1), new BigDecimal("1000")),
                new Person(2L, "Jane", "Doe", LocalDate.of(1985, 1, 1), new BigDecimal("2000")),
                new Person(3L, "Jim", "Doe", LocalDate.of(1990, 1, 1), new BigDecimal("3000")),
                new Person(4L, "Jill", "Doe", LocalDate.of(1995, 1, 1), new BigDecimal("4000"))
        );
        model.addAttribute("people", people);
        return "people";
    }
}
