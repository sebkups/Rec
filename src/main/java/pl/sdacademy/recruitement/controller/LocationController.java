package pl.sdacademy.recruitement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.recruitement.entity.Location;
import pl.sdacademy.recruitement.service.LocationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/load")
    public void fetchDataFromFile() {
        locationService.loadDataAndSaveInDb();
    }

    @GetMapping
    public List<Location> getDataFromDb(@RequestParam(required = false) String city) {
        return locationService.fetchData(city);
    }

    @GetMapping("/{id}")
    public Location getById(@PathVariable Long id) {
        return locationService.find(id);
    }

    @PostMapping
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        return locationService.update(id, location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        locationService.delete(id);
    }

}
