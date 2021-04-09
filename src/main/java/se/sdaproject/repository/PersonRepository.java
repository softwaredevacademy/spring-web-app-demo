package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
