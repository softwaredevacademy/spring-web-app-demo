package se.sdaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sdaproject.api.exception.ResourceNotFoundException;
import se.sdaproject.model.Person;
import se.sdaproject.repository.PersonRepository;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        personRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedPerson.setId(id);
        Person person = personRepository.save(updatedPerson);
        return person;
    }

}
