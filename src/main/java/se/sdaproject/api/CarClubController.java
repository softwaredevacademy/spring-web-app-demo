package se.sdaproject.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.api.exception.ResourceNotFoundException;
import se.sdaproject.model.CarClub;
import se.sdaproject.model.Person;
import se.sdaproject.repository.CarClubRepository;
import se.sdaproject.repository.PersonRepository;

import java.util.List;

@RestController
public class CarClubController {

    CarClubRepository carClubRepository;
    PersonRepository personRepository;

    public CarClubController(CarClubRepository carClubRepository, PersonRepository personRepository) {
        this.carClubRepository = carClubRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("car-clubs")
    public ResponseEntity<CarClub> createCarClub(@RequestBody CarClub carClub) {
        carClubRepository.save(carClub);
        return ResponseEntity.status(HttpStatus.CREATED).body(carClub);
    }

    @PostMapping("car-clubs/{carClubId}/members/{personId}")
    public ResponseEntity<CarClub> createMembership(@PathVariable Long carClubId, @PathVariable Long personId) {
        CarClub carClub = carClubRepository.findById(carClubId).orElseThrow(ResourceNotFoundException::new);
        Person person = personRepository.findById(personId).orElseThrow(ResourceNotFoundException::new);
        carClub.getMembers().add(person);
        carClubRepository.save(carClub);
        return ResponseEntity.status(HttpStatus.CREATED).body(carClub);
    }

    @GetMapping("car-clubs/{carClubId}/members")
    public ResponseEntity<List<Person>> listCarClubMembers(@PathVariable Long carClubId) {
        CarClub carClub = carClubRepository.findById(carClubId).orElseThrow(ResourceNotFoundException::new);
        List<Person> members = carClub.getMembers();
        return ResponseEntity.ok(members);
    }

    @DeleteMapping("car-clubs/{carClubId}/members/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMembership(@PathVariable Long carClubId, @PathVariable Long personId) {
        CarClub carClub = carClubRepository.findById(carClubId).orElseThrow(ResourceNotFoundException::new);
        Person person = personRepository.findById(personId).orElseThrow(ResourceNotFoundException::new);
        if (carClub.getMembers().contains(person)) {
            carClub.getMembers().remove(person);
            carClubRepository.save(carClub);
        } else{
            throw new ResourceNotFoundException();
        }
    }

    @DeleteMapping("car-clubs/{carClubId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarClub(@PathVariable Long carClubId) {
        CarClub carClub = carClubRepository.findById(carClubId).orElseThrow(ResourceNotFoundException::new);
        carClubRepository.delete(carClub);
    }

}
