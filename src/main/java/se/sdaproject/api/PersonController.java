package se.sdaproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.model.Person;
import se.sdaproject.repository.PersonRepository;
import se.sdaproject.api.exception.ResourceNotFoundException;
import se.sdaproject.service.PersonService;

import java.util.List;

@RequestMapping("/people")
@RestController
public class PersonController {

    PersonRepository personRepository;
    PersonService personService;

    @Autowired
    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> listAllPeople() {
        List<Person> people = personRepository.findAll();
        return people;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        personRepository.delete(person);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

}
