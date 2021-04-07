package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
