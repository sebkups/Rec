package pl.sdacademy.recruitement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.recruitement.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByCityIgnoringCaseOrderByCompanyNameAsc(String cityName);
}
