package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sdaproject.model.CarClub;

@Repository
public interface CarClubRepository extends JpaRepository<CarClub, Long> {
}
