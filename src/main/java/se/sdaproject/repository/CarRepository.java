package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
