package com.lingarogroup.peopledbweb.web.controller;

import com.lingarogroup.peopledbweb.biz.model.Person;
import com.lingarogroup.peopledbweb.data.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ModelAttribute("people")
    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }

    @ModelAttribute
    public Person getPerson() {
        return new Person();
    }
    @GetMapping
    public String showPeoplePage() {
        return "people";
    }

    @PostMapping
//    @Valid annotation tells Spring MVC to validate the Person object before calling the savePerson method.
//    The validation rules are made by annotations in Person class itself
    public String savePerson(@Valid Person person, Errors errors) {
        if (!errors.hasErrors()) {
            personRepository.save(person);
//        redirect: is a special prefix that tells Spring MVC to redirect to a different route.
//        in this case route is the same, but it refreshes the page
            return "redirect:people";
        }
        return "people";
    }
}
