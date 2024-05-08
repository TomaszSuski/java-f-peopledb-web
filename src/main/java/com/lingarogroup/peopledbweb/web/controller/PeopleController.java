package com.lingarogroup.peopledbweb.web.controller;

import com.lingarogroup.peopledbweb.biz.model.Person;
import com.lingarogroup.peopledbweb.data.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

//    adding params = "delete=true" to the @PostMapping annotation tells Spring MVC to only call the deletePeople method
//    when the delete parameter is present in the request.
    @PostMapping(params = "delete=true")
//    @RequestParam annotation tells Spring MVC to bind the request parameter selections to the selections parameter.
//    if params names don't match, you can use @RequestParam("paramName") List<Long> anotherName
//    @RequestParam(required = false) means that the parameter is optional = it turns nothing into null.
//    But it's also possible and probably better to use Optional<List<Long>> selections
    public String deletePeople(@RequestParam Optional<List<Long>> selections) {
        selections.ifPresent(personRepository::deleteAllById);
        return "redirect:people";
    }

    @PostMapping(params = "edit")
    public String editPerson(@RequestParam("edit") Long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow();
        model.addAttribute("person", person);
        return "people";
    }
}
