package com.lingarogroup.peopledbweb.web.controller;

import com.lingarogroup.peopledbweb.biz.model.Person;
import com.lingarogroup.peopledbweb.data.PersonRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


/**
 * The PeopleController class is a Spring MVC Controller that handles HTTP requests related to Person objects.
 * It uses the PersonRepository to perform CRUD operations on Person objects.
 * It is mapped to the "/people" URL path.
 */
@Controller
@RequestMapping("/people")
@Log4j2
public class PeopleController {
    private PersonRepository personRepository;

    /**
     * The constructor for the PeopleController class.
     * It initializes the PersonRepository used by this controller.
     *
     * @param personRepository The PersonRepository to be used by this controller.
     */
    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * This method is annotated with @ModelAttribute and is used to retrieve all Person objects from the repository.
     * The returned Iterable<Person> is automatically added to the Model under the attribute name "people".
     * This attribute can then be accessed in the view.
     *
     * @return An Iterable of all Person objects in the repository.
     */
    @ModelAttribute("people")
    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }

    /**
     * This method is annotated with @ModelAttribute and is used to create a new Person object.
     * The created Person object is automatically added to the Model under the attribute name "person".
     * This attribute can then be accessed in the view, typically for form binding purposes.
     *
     * @return A new Person object.
     */
    @ModelAttribute
    public Person getPerson() {
        return new Person();
    }

    /**
     * This method is mapped to the HTTP GET method and is used to display the "people" view.
     * It does not require any parameters and does not perform any operations on the model or the repository.
     * It simply returns the name of the view to be rendered, in this case "people".
     *
     * @return The name of the view to be rendered. In this case, it's "people".
     */
    @GetMapping
    public String showPeoplePage() {
        return "people";
    }

    /**
     * This method is mapped to the HTTP POST method and is used to save a Person object to the repository.
     * It first validates the Person object using the @Valid annotation. If there are no errors, the Person object is saved to the repository.
     * After saving, it redirects to the "people" view. If there are errors, it simply returns the "people" view without saving the Person object.
     *
     * @param person The Person object to be saved. This object is validated before the savePerson method is called.
     *               The validation rules are defined by annotations in the Person class itself.
     * @param errors The Errors object provided by Spring MVC. This object contains any validation errors that occurred when validating the Person object.
     * @return       A String representing the redirect route to the "people" view if there are no errors, or the "people" view itself if there are errors.
     */
    @PostMapping
    //    @Valid annotation tells Spring MVC to validate the Person object before calling the savePerson method.
    //    The validation rules are made by annotations in Person class itself
    //  @RequestParam annotation tells Spring MVC to bind the request parameter photoFileName to the photoFileName parameter.
    //  The MultipartFile type represents a file uploaded as part of a multipart request. It binds binary data to the photoFileName parameter.
    public String savePerson(@Valid Person person, Errors errors, @RequestParam MultipartFile photoFileName) {
        log.info("photoFileName: " + photoFileName.getOriginalFilename());
        log.info("photoFileSize: " + photoFileName.getSize());
        if (!errors.hasErrors()) {
            personRepository.save(person);
    //        redirect: is a special prefix that tells Spring MVC to redirect to a different route.
    //        in this case route is the same, but it refreshes the page
            return "redirect:people";
        } else {
            log.info("Validation errors: " + errors.getAllErrors());
        }
        return "people";
    }

    /**
     * This method is mapped to the HTTP POST method and is only invoked when the "delete=true" parameter is present in the request.
     * It retrieves a list of Person object ids from the request, and if present, deletes all corresponding Person objects from the repository.
     * After deletion, it redirects to the "people" view.
     *
     * @param selections An Optional list of Person object ids to be deleted. This is provided as a request parameter named "selections".
     *                   If the parameter is not present in the request, the Optional will be empty, and no deletion will occur.
     * @return           A String representing the redirect route to the "people" view.
     */
    // adding params = "delete=true" to the @PostMapping annotation tells Spring MVC to only call the deletePeople method
    // when the delete parameter is present in the request.
    @PostMapping(params = "delete=true")
    // @RequestParam annotation tells Spring MVC to bind the request parameter selections to the selections parameter.
    // if params names don't match, you can use @RequestParam("paramName") List<Long> anotherName
    // @RequestParam(required = false) means that the parameter is optional = it turns nothing into null.
    // But it's also possible and probably better to use Optional<List<Long>> selections
    public String deletePeople(@RequestParam Optional<List<Long>> selections) {
        selections.ifPresent(personRepository::deleteAllById);
        return "redirect:people";
    }

    /**
     * This method is mapped to the HTTP POST method and is only invoked when the "edit" parameter is present in the request.
     * It retrieves a Person object from the repository using the provided id, adds it to the model, and returns the name of the view (in this case "people").
     *
     * @param id    The id of the Person object to be edited. This is provided as a request parameter named "edit".
     * @param model The Model object provided by Spring MVC. The Person object is added to this model and then accessed in the view.
     * @return      The name of the view to be rendered. In this case, it's "people".
     */
    @PostMapping(params = "edit")
    public String editPerson(@RequestParam("edit") Long id, Model model) {
        // Retrieve the Person object with the provided id from the repository.
        // If no such Person exists, an exception is thrown.
        Person person = personRepository.findById(id).orElseThrow();

        // Add the retrieved Person object to the model.
        model.addAttribute("person", person);

        // Return the name of the view to be rendered.
        return "people";
    }

    /**
     * This method is mapped to the HTTP POST method and is only invoked when the "cancel=true" parameter is present in the request.
     * It does not perform any operations on the model or the repository.
     * It simply redirects to the "people" view, effectively cancelling any ongoing edit operation.
     *
     * @return A String representing the redirect route to the "people" view.
     */
    @PostMapping(params = "cancel=true")
    public String cancelEdit() {
        return "redirect:people";
    }
}
