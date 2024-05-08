package com.lingarogroup.peopledbweb.data;

import com.lingarogroup.peopledbweb.biz.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The PersonRepository interface is a Spring Data JPA repository for Person objects.
 * It extends the CrudRepository interface from Spring Data JPA, which provides methods for CRUD operations.
 * The @Repository annotation tells Spring that this interface is a repository, which allows it to be autowired into other components.
 *
 * @see org.springframework.data.repository.CrudRepository
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
