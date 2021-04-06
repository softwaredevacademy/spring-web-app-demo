package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/people")
    public List<Person> listAllPeople() {
        List<Person> people = personRepository.findAll();
        return people;
    }

    @RequestMapping("/people/create/{name}/{age}")
    public Person createPerson(@PathVariable("name") String name, @PathVariable("age") int age) {
        Person person = new Person(name, age);
        personRepository.save(person);
        return person;
    }

}
