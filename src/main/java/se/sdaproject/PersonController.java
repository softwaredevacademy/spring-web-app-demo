package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RequestMapping("/people")
@RestController
public class PersonController {

    PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
        personRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedPerson.setId(id);
        Person person = personRepository.save(updatedPerson);
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
