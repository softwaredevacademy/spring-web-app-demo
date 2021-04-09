package se.sdaproject.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.sdaproject.repository.CarRepository;
import se.sdaproject.repository.PersonRepository;
import se.sdaproject.api.exception.ResourceNotFoundException;
import se.sdaproject.model.Car;
import se.sdaproject.model.Person;

import javax.validation.Valid;

@Controller
public class CarController {

    CarRepository carRepository;
    PersonRepository personRepository;

    public CarController(CarRepository carRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("/people/{personId}/cars")
    public ResponseEntity<Car> createCar(@PathVariable Long personId, @RequestBody Car car) {
        Person person = personRepository
                .findById(personId)
                .orElseThrow(ResourceNotFoundException::new);
        car.setOwner(person);
        carRepository.save(car);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(car);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @Valid @RequestBody Car updatedCar) {
        Car car = carRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedCar.setId(id);
        carRepository.save(updatedCar);
        return ResponseEntity.ok(updatedCar);
    }

}
