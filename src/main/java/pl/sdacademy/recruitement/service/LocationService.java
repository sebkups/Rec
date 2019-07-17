package pl.sdacademy.recruitement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sdacademy.recruitement.exception.LocationNotFoundException;
import pl.sdacademy.recruitement.repository.LocationRepository;
import pl.sdacademy.recruitement.entity.Location;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final FileDataService fileDataService;
    private final LocationRepository locationRepository;

    public List<Location> fetchData(String city) {
        if (city != null && !city.isEmpty()) {
            return locationRepository.findByCityIgnoringCaseOrderByCompanyNameAsc(city);
        }

        Sort sort = Sort.by(Sort.Direction.ASC, "companyName");
        return locationRepository.findAll(sort);
    }

    @Transactional
    public void loadDataAndSaveInDb() {
        List<Location> fileData = fileDataService.fetchRetailLocations();
        locationRepository.deleteAllInBatch();
        locationRepository.saveAll(fileData);
    }

    public Location find(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    public Location update(Long id, Location location) {
        Location entity = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        entity.setAddressDescription(location.getAddressDescription());
        entity.setBuildingNumber(location.getBuildingNumber());
        entity.setCity(location.getCity());
        entity.setCompanyName(location.getCompanyName());
        entity.setLocationName(location.getLocationName());
        entity.setLocationType(location.getLocationType());
        entity.setRoomNumber(location.getRoomNumber());
        entity.setStreet(location.getStreet());

        return entity;
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
