package com.lingarogroup.peopledbweb.data;

import com.lingarogroup.peopledbweb.biz.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class PersonDataLoader implements ApplicationRunner {
    private PersonRepository personRepository;

    public PersonDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (personRepository.count() == 0) {
            List<Person> people = List.of(
                    new Person(null, "John", "Doe", LocalDate.of(1980, 1, 1), new BigDecimal("1000")),
                    new Person(null, "Jane", "Doe", LocalDate.of(1985, 1, 1), new BigDecimal("2000")),
                    new Person(null, "Jim", "Jackson", LocalDate.of(1990, 1, 1), new BigDecimal("3000")),
                    new Person(null, "Jill", "Jackson", LocalDate.of(1995, 1, 1), new BigDecimal("4000")),
                    new Person(null, "Jack", "Smith", LocalDate.of(2000, 1, 1), new BigDecimal("5000")),
                    new Person(null, "Jenny", "Doe", LocalDate.of(2005, 1, 1), new BigDecimal("6000")),
                    new Person(null, "Jerry", "Jackson", LocalDate.of(2010, 1, 1), new BigDecimal("7000"))
            );
            personRepository.saveAll(people);
        }
    }
}
